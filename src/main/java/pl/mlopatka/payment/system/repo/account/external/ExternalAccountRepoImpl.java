package pl.mlopatka.payment.system.repo.account.external;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;

import java.util.List;
import java.util.Optional;

public class ExternalAccountRepoImpl implements ExternalAccountRepo {

    @Override
    public Optional<ExternalAccount> findAccount(String accountNumber, String currency, Session session) {
        String hql = "from InternalAccount as account where account.accountNumber = " + accountNumber + " and account.currency like "
                + currency;
        Query query = session.createQuery(hql);
        List result = query.list();
        if(result.size() > 1) {
            throw new RuntimeException("There should be only one customer with accountNumber " + accountNumber);
        }

        return Optional.ofNullable((ExternalAccount) result.get(0));
    }
}
