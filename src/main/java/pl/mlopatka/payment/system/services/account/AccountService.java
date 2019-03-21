package pl.mlopatka.payment.system.services.account;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.Account;
import pl.mlopatka.payment.system.model.ExternalAccountDto;
import pl.mlopatka.payment.system.model.InternalAccountDto;
import pl.mlopatka.payment.system.util.AccountType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findInternalAccount(int id, String currency, Session session);

    AccountType checkAccountType(String accountNumber, String currency, Session session);

    void updateAccount(String accountNumber, BigDecimal amount, Session session);

    void externalTransfer(String accountNumber, BigDecimal amount);

    List<InternalAccountDto> getAllInternals();

    List<ExternalAccountDto> getAllExternals();
}
