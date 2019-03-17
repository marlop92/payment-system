package pl.mlopatka.payment.system.services.transfer;

import org.javamoney.moneta.Money;
import pl.mlopatka.payment.system.exceptions.AccountNotFoundException;
import pl.mlopatka.payment.system.exceptions.InvalidBalanceException;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;
import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.repo.TransferRepository;
import pl.mlopatka.payment.system.services.account.external.ExternalAccountService;
import pl.mlopatka.payment.system.services.account.internal.InternalAccountService;
import pl.mlopatka.payment.system.util.TransferUtil;

import java.time.ZonedDateTime;

//TODO create single account service and fetch from internal and external when using acc nr and currency method
public class TransferServiceImpl implements TransferService {

    private static final String ACCOUNT_NOT_FOUND = "Unable to find account with specified parameters";
    private static final String NOT_ENOUGH_FOUNDS = "Customer doesn't have enough money to do the transfer";

    private final TransferRepository transferRepository;
    private final InternalAccountService internalAccountService;
    private final ExternalAccountService externalAccountService;

    public TransferServiceImpl(final TransferRepository transferRepository,
                               final InternalAccountService internalAccountService,
                               final ExternalAccountService externalAccountService) {
        this.transferRepository = transferRepository;
        this.internalAccountService = internalAccountService;
        this.externalAccountService = externalAccountService;
    }

    public void transferMoney(final TransferRequest transferRequest, final ZonedDateTime transferDate) {
        InternalAccount senderAccount = internalAccountService.findAccount(transferRequest.getCid(),
                transferRequest.getCurrency()).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND));

        Money transferAmount = Money.of(transferRequest.getAmount(), transferRequest.getCurrency());
        if (senderAccount.getBalance().isLessThan(transferAmount)) {
            throw new InvalidBalanceException(NOT_ENOUGH_FOUNDS);
        }

        ExternalAccount receiverAccount = externalAccountService.findAccount(transferRequest.getReceiverAccount(),
                transferRequest.getCurrency()).orElseThrow(() -> new AccountNotFoundException((ACCOUNT_NOT_FOUND)));

        TransferCandidate transferCandidate = TransferUtil.createTransferCandidate(senderAccount, receiverAccount,
                transferAmount, transferRequest.getTitle(), transferDate);

        transferRepository.saveTransfer(transferCandidate);
        transferRepository.updateBalance(transferCandidate);
    }
}
