package pl.mlopatka.payment.system.services.transfer;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mlopatka.payment.system.exceptions.AccountNotFoundException;
import pl.mlopatka.payment.system.exceptions.InvalidBalanceException;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.entities.Customer;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;
import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.repo.TransferRepository;
import pl.mlopatka.payment.system.services.account.external.ExternalAccountService;
import pl.mlopatka.payment.system.services.account.internal.InternalAccountService;

import javax.money.Monetary;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransferServiceTest {

    @Mock
    TransferRepository transferRepository;

    @Mock
    InternalAccountService internalAccountService;

    @Mock
    ExternalAccountService externalAccountService;

    @InjectMocks
    TransferServiceImpl transferService;

    @Before
    public void setOff() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validRequestShouldSaveTransferAndUpdateBalance() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        InternalAccount senderAccount = new InternalAccount(1, new Customer(1), "1200000012345678",
                Money.of(new BigDecimal(1000.00), "PLN"));
        ExternalAccount receiverAccount = new ExternalAccount(1, "1000000012345678", Monetary.getCurrency("PLN"));
        ZonedDateTime now = ZonedDateTime.now();

        when(internalAccountService.findAccount(1, "PLN")).thenReturn(Optional.of(senderAccount));
        when(externalAccountService.findAccount("1000000012345678", "PLN"))
                .thenReturn(Optional.of(receiverAccount));

        TransferCandidate transferCandidate = new TransferCandidate("1200000012345678", "1000000012345678",
                "Money Transfer", Money.of(new BigDecimal(100.00), "PLN"), now);

        //when
        transferService.transferMoney(transferRequest, now);

        //than
        verify(transferRepository).updateBalance(transferCandidate);
        verify(transferRepository).saveTransfer(transferCandidate);
    }

    @Test(expected = AccountNotFoundException.class)
    public void invalidSenderAccountShouldThrowException() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        ZonedDateTime now = ZonedDateTime.now();

        //when
        transferService.transferMoney(transferRequest, now);

        //than - expect exception
    }

    @Test(expected = InvalidBalanceException.class)
    public void transferMoneyWithAmountGreaterThanBalanceShouldThrowException() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        InternalAccount senderAccount = new InternalAccount(1, new Customer(1), "1200000012345678",
                Money.of(new BigDecimal(50.00), "PLN"));
        ZonedDateTime now = ZonedDateTime.now();

        when(internalAccountService.findAccount(1, "PLN")).thenReturn(Optional.of(senderAccount));

        //when
        transferService.transferMoney(transferRequest, now);

        //than - expect exception
    }

    @Test(expected = AccountNotFoundException.class)
    public void invalidReceiverAccountShouldThrowException() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1000000012345678",
                "Money Transfer", new BigDecimal(100.00), "PLN");
        InternalAccount senderAccount = new InternalAccount(1, new Customer(1), "1200000012345678",
                Money.of(new BigDecimal(1000.00), "PLN"));
        ZonedDateTime now = ZonedDateTime.now();

        when(internalAccountService.findAccount(1, "PLN")).thenReturn(Optional.of(senderAccount));

        //when
        transferService.transferMoney(transferRequest, now);

        //than - expect exception
    }
}