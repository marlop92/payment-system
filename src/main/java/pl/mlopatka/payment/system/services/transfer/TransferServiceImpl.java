package pl.mlopatka.payment.system.services.transfer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.mlopatka.payment.system.exceptions.InvalidRestTransaction;
import pl.mlopatka.payment.system.exceptions.InvalidResultException;
import pl.mlopatka.payment.system.exceptions.InvalidTransactionState;
import pl.mlopatka.payment.system.model.Account;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.repo.transfer.TransferRepository;
import pl.mlopatka.payment.system.repo.transfer.TransferRepositoryImpl;
import pl.mlopatka.payment.system.services.account.AccountService;
import pl.mlopatka.payment.system.services.account.AccountServiceImpl;
import pl.mlopatka.payment.system.util.AccountType;
import pl.mlopatka.payment.system.util.HibernateLifecycleUtil;
import pl.mlopatka.payment.system.util.TransferUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferServiceImpl implements TransferService {

    private static final String ACCOUNT_NOT_FOUND = "Unable to find account with specified parameters";
    private static final String NOT_ENOUGH_MONEY = "Customer doesn't have enough money to do the transfer";
    private static final int EQUALITY = 0;
    private static final String INVALID_RECEIVER_ACCOUNT = "Receiver of Money Transfer cannot have the same account number as a sender";
    private static final String INTERRUPTED_TRANSACTION = "Transaction interrupted: ";

    private final TransferRepository transferRepository;
    private final AccountService accountService;

    public TransferServiceImpl() {
        this.transferRepository = new TransferRepositoryImpl();
        this.accountService = new AccountServiceImpl();
    }

    public void transferMoney(final TransferRequest transferRequest, final LocalDateTime transferDate) {
        Session session = HibernateLifecycleUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Account senderAccount = accountService.findInternalAccount(transferRequest.getCid(),
                    transferRequest.getCurrency(), session)
                    .orElseThrow(() -> new InvalidTransactionState(ACCOUNT_NOT_FOUND));
            BigDecimal amount = transferRequest.getAmount();

            if(senderAccount.getAccountNumber().equals(transferRequest.getReceiverAccount())) {
                throw new InvalidTransactionState(INVALID_RECEIVER_ACCOUNT);
            }

            if (senderAccount.getBalance().compareTo(amount) <= EQUALITY) {
                throw new InvalidTransactionState(NOT_ENOUGH_MONEY);
            }

            AccountType receiverAccountType = accountService.checkAccountType(transferRequest.getReceiverAccount(),
                    transferRequest.getCurrency(), session);
            if (AccountType.NONE.equals(receiverAccountType)) {
                throw new InvalidTransactionState(ACCOUNT_NOT_FOUND);
            }

            TransferCandidate transferCandidate = TransferUtil.createTransferCandidate(senderAccount,
                    transferRequest.getReceiverAccount(), transferRequest.getTitle(), amount, transferDate);

            transferRepository.saveTransfer(transferCandidate, session);
            accountService.updateAccount(senderAccount.getAccountNumber(), transferRequest.getAmount().negate(), session);
            if (AccountType.INTERNAL.equals(receiverAccountType)) {
                accountService.updateAccount(transferRequest.getReceiverAccount(), transferRequest.getAmount(), session);
            } else {
                accountService.externalTransfer(transferRequest.getReceiverAccount(), transferRequest.getAmount());
            }
            tx.commit();

        } catch (InvalidResultException | InvalidTransactionState ex) {
            tx.rollback();
            throw new InvalidRestTransaction(INTERRUPTED_TRANSACTION + ex.getMessage());
        } catch (RuntimeException ex) {
            tx.rollback();
            throw new RuntimeException(INTERRUPTED_TRANSACTION + ex.getMessage());
        }
        finally {
            session.close();
        }
    }
}
