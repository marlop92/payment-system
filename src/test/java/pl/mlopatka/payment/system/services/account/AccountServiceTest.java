package pl.mlopatka.payment.system.services.account;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import pl.mlopatka.payment.system.model.Account;
import pl.mlopatka.payment.system.model.entities.Customer;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;
import pl.mlopatka.payment.system.repo.account.external.ExternalAccountRepo;
import pl.mlopatka.payment.system.repo.account.internal.InternalAccountRepo;
import pl.mlopatka.payment.system.util.AccountType;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    InternalAccountRepo internalAccountRepo = mock(InternalAccountRepo.class);
    ExternalAccountRepo externalAccountRepo = mock(ExternalAccountRepo.class);
    Session session = mock(Session.class);

    AccountService accountService;

    @Before
    public void setOff() {
        accountService = new AccountServiceImpl(internalAccountRepo, externalAccountRepo);
    }

    @Test
    public void validInputShouldReturnExistingAccount() {
        //given
        int id = 1;
        String currency = "PLN";
        InternalAccount account = new InternalAccount(new Customer(), "1234567890123456",
                new BigDecimal(10.0), Currency.getInstance("PLN"));
        Account expectedAccount = new Account("1234567890123456", new BigDecimal(10.0), Currency.getInstance("PLN"));

        when(internalAccountRepo.findAccount(1, "PLN", session)).thenReturn(Optional.ofNullable(account));

        //when
        Optional<Account> result = accountService.findInternalAccount(id, currency, session);

        //than
        assertEquals(expectedAccount, result.get());
    }

    @Test
    public void nonExistingUserShouldReturnEmptyOptional() {
        //given
        int id = 1;
        String currency = "PLN";

        when(internalAccountRepo.findAccount(1, "PLN", session)).thenReturn(Optional.ofNullable(null));

        //when
        Optional<Account> result = accountService.findInternalAccount(id, currency, session);

        //than
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void internalAccountShouldReturnInternalAccountType() {
        //given
        String accountNr = "1234";
        String currency = "PLN";
        Optional<InternalAccount> account = Optional.ofNullable(new InternalAccount());
        AccountType expectedType = AccountType.INTERNAL;

        when(internalAccountRepo.findAccount("1234", "PLN", session)).thenReturn(account);

        //when
        AccountType result = accountService.checkAccountType(accountNr, currency, session);

        //than
        assertEquals(result, expectedType);
    }

    @Test
    public void externalAccountShouldReturnExternalAccountType() {
        //given
        String accountNr = "1234";
        String currency = "PLN";
        Optional<ExternalAccount> account = Optional.ofNullable(new ExternalAccount());
        AccountType expectedType = AccountType.EXTERNAL;

        when(internalAccountRepo.findAccount("1234", "PLN", session)).thenReturn(Optional.empty());
        when(externalAccountRepo.findAccount("1234", "PLN", session)).thenReturn(account);

        //when
        AccountType result = accountService.checkAccountType(accountNr, currency, session);

        //than
        assertEquals(result, expectedType);
    }

    @Test
    public void nonExistingAccountShouldReturnNoneAccountType() {
        //given
        String accountNr = "1234";
        String currency = "PLN";
        AccountType expectedType = AccountType.NONE;

        when(internalAccountRepo.findAccount("1234", "PLN", session)).thenReturn(Optional.empty());
        when(externalAccountRepo.findAccount("1234", "PLN", session)).thenReturn(Optional.empty());

        //when
        AccountType result = accountService.checkAccountType(accountNr, currency, session);

        //than
        assertEquals(result, expectedType);
    }

}