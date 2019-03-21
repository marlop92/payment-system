package pl.mlopatka.payment.system.repo.account.external;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;

import java.util.List;
import java.util.Optional;

public interface ExternalAccountRepo {
    Optional<ExternalAccount> findAccount(String accountNumber, String currency, Session session);

    List<ExternalAccount> getAll();
}
