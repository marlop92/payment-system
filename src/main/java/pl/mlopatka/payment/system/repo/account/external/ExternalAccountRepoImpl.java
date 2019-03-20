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
        String hql = "from ExternalAccount where accountNumber = " + accountNumber + " and currency = " +
                "'" + currency + "'";
        Query query = session.createQuery(hql);
        List<?> result = query.list();
        if(result.size() > 1) {
            throw new RuntimeException("There should be only one customer with accountNumber " + accountNumber);
        }

        return result.stream().map(n -> (ExternalAccount) n).findAny();
    }
}
