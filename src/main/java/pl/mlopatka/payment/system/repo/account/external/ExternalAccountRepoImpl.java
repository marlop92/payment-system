package pl.mlopatka.payment.system.repo.account.external;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.mlopatka.payment.system.exceptions.InvalidResultException;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;
import pl.mlopatka.payment.system.util.HibernateLifecycleUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExternalAccountRepoImpl implements ExternalAccountRepo {

    private static final String INVALID_USERS_ACCOUNTS = "Improper number of users having the same";

    @Override
    public Optional<ExternalAccount> findAccount(final String accountNumber, final String currency,
                                                 final Session session) {
        String hql = "from ExternalAccount where accountNumber = " + accountNumber + " and currency = " +
                "'" + currency + "'";
        Query query = session.createQuery(hql);
        List<?> result = query.list();
        if(result.size() > 1) {
            throw new InvalidResultException(INVALID_USERS_ACCOUNTS  + " " + accountNumber);
        }

        return result.stream().map(n -> (ExternalAccount) n).findAny();
    }

    @Override
    public List<ExternalAccount> getAll() {
        Session session = HibernateLifecycleUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from ExternalAccount");
        List<?> result = query.list();
        List<ExternalAccount> accounts = result.stream().map(n -> (ExternalAccount) n).collect(Collectors.toList());
        session.close();

        return accounts;
    }

}
