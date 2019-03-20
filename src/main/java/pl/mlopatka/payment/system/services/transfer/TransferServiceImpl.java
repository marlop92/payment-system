package pl.mlopatka.payment.system.services.transfer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.mlopatka.payment.system.exceptions.AccountNotFoundException;
import pl.mlopatka.payment.system.exceptions.InvalidBalanceException;
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

//TODO create single account service and fetch from internal and external when using acc nr and currency method
public class TransferServiceImpl implements TransferService {

    private static final String ACCOUNT_NOT_FOUND = "Unable to find account with specified parameters";
    private static final String NOT_ENOUGH_FOUNDS = "Customer doesn't have enough money to do the transfer";
    private static final int EQUALITY = 0;

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
                    transferRequest.getCurrency(), session).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND));
            BigDecimal amount = transferRequest.getAmount();

            if(senderAccount.equals(transferRequest.getReceiverAccount())) {
                throw new RuntimeException("Can't transfer money to own account");
            }

            if (senderAccount.getBalance().compareTo(amount) <= EQUALITY) {
                throw new InvalidBalanceException(NOT_ENOUGH_FOUNDS);
            }

            AccountType receiverAccountType = accountService.checkAccountType(transferRequest.getReceiverAccount(),
                    transferRequest.getCurrency(), session);
            if (AccountType.NONE.equals(receiverAccountType)) {
                throw new AccountNotFoundException(ACCOUNT_NOT_FOUND);
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
        } catch (RuntimeException ex) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
