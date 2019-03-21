package pl.mlopatka.payment.system.repo.account.internal;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.entities.InternalAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InternalAccountRepo {
    Optional<InternalAccount> findAccount(int cid, String currency, Session session);

    Optional<InternalAccount> findAccount(String accountNumber, String currency, Session session);

    void updateAccountBalance(String accountNumber, BigDecimal amount, Session session);

    List<InternalAccount> getAll();
}
