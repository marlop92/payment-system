package pl.mlopatka.payment.system.services.transfer;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import pl.mlopatka.payment.system.exceptions.InvalidTransactionState;
import pl.mlopatka.payment.system.model.Account;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.repo.transfer.TransferRepository;
import pl.mlopatka.payment.system.services.account.AccountService;
import pl.mlopatka.payment.system.util.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransferServiceTest {
    AccountService accountService = mock(AccountService.class);
    TransferRepository transferRepository = mock(TransferRepository.class);
    Session session = mock(Session.class);

    TransferService transferService;

    @Before
    public void setOff() {
        transferService = new TransferServiceImpl(transferRepository, accountService);
    }

    @Test
    public void validRequestShouldSaveTransferAndUpdateBalanceForExternalReceiver() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        Account senderAccount = new Account("1200000012345678", new BigDecimal(1000.00), Currency.getInstance("PLN"));
        AccountType receiverAccountType = AccountType.EXTERNAL;
        LocalDateTime now = LocalDateTime.now();

        when(accountService.findInternalAccount(1, "PLN", session)).thenReturn(Optional.of(senderAccount));
        when(accountService.checkAccountType("1000000012345678", "PLN", session))
                .thenReturn(receiverAccountType);

        TransferCandidate transferCandidate = new TransferCandidate("1200000012345678", "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), Currency.getInstance("PLN"), now);

        //when
        transferService.transferMoney(transferRequest, now, session);

        //than
        verify(transferRepository).saveTransfer(transferCandidate, session);
        verify(accountService).updateAccount(senderAccount.getAccountNumber(), transferRequest.getAmount().negate(), session);
        verify(accountService).externalTransfer(transferRequest.getReceiverAccount(), transferRequest.getAmount());
    }

    @Test
    public void validRequestShouldSaveTransferAndUpdateBalanceForInternalReceiver() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        Account senderAccount = new Account("1200000012345678", new BigDecimal(1000.00), Currency.getInstance("PLN"));
        AccountType receiverAccountType = AccountType.INTERNAL;
        LocalDateTime now = LocalDateTime.now();

        when(accountService.findInternalAccount(1, "PLN", session)).thenReturn(Optional.of(senderAccount));
        when(accountService.checkAccountType("1000000012345678", "PLN", session))
                .thenReturn(receiverAccountType);

        TransferCandidate transferCandidate = new TransferCandidate("1200000012345678", "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), Currency.getInstance("PLN"), now);

        //when
        transferService.transferMoney(transferRequest, now, session);

        //than
        verify(transferRepository).saveTransfer(transferCandidate, session);
        verify(accountService).updateAccount(senderAccount.getAccountNumber(), transferRequest.getAmount().negate(), session);
        verify(accountService).updateAccount(transferRequest.getReceiverAccount(), transferRequest.getAmount(), session);
    }



    @Test(expected = InvalidTransactionState.class)
    public void invalidSenderAccountShouldThrowException() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        LocalDateTime now = LocalDateTime.now();
        when(accountService.findInternalAccount(1, "PLN", session)).thenReturn(Optional.empty());

        //when
        transferService.transferMoney(transferRequest, now, session);

        //than - expect exception
    }

    @Test(expected = InvalidTransactionState.class)
    public void transferMoneyWithAmountGreaterThanBalanceShouldThrowException() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        Account senderAccount = new Account("1200000012345678", new BigDecimal(50.00), Currency.getInstance("PLN"));
        LocalDateTime now = LocalDateTime.now();

        when(accountService.findInternalAccount(1, "PLN", session)).thenReturn(Optional.of(senderAccount));

        //when
        transferService.transferMoney(transferRequest, now, session);

        //than - expect exception
    }

    @Test(expected = InvalidTransactionState.class)
    public void invalidReceiverAccountShouldThrowException() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        Account senderAccount = new Account("1200000012345678", new BigDecimal(50.00), Currency.getInstance("PLN"));
        LocalDateTime now = LocalDateTime.now();
        AccountType receiverAccountType = AccountType.NONE;

        when(accountService.findInternalAccount(1, "PLN", session)).thenReturn(Optional.of(senderAccount));
        when(accountService.checkAccountType("1000000012345678", "PLN", session))
                .thenReturn(receiverAccountType);

        //when
        transferService.transferMoney(transferRequest, now, session);

        //than - expect exception
    }
}
