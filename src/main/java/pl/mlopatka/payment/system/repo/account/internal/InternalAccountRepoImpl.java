package pl.mlopatka.payment.system.repo.account.internal;

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
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InternalAccountRepoImpl implements InternalAccountRepo {

    private static final String INVALID_USERS_VALUES = "Improper number of users having the same";

    @Override
    public Optional<InternalAccount> findAccount(final int cid, final String currency, final Session session) {
        String hql = "from InternalAccount as account where account.customer.id = " + cid + " and account.currency like "
                + "'" + currency + "'";
        Query query = session.createQuery(hql);
        List<?> result = query.list();
        if(result.size() > 1) {
            throw new InvalidResultException(INVALID_USERS_VALUES + " " + cid);
        }

        return result.stream().map(n -> (InternalAccount) n).findAny();
    }

    @Override
    public Optional<InternalAccount> findAccount(final String accountNumber, final String currency,
                                                 final Session session) {
        String hql = "from InternalAccount as account where account.accountNumber = " + accountNumber +
                " and account.currency like " + "'" + currency + "'";
        Query query = session.createQuery(hql);
        List<?> result = query.list();
        if(result.size() > 1) {
            throw new InvalidResultException(INVALID_USERS_VALUES + " " + accountNumber);
        }

        return result.stream().map(n -> (InternalAccount) n).findAny();
    }

    @Override
    public void updateAccountBalance(final String accountNumber, final BigDecimal amount, final Session session) {
        String hql = "from InternalAccount as account where account.accountNumber = " + accountNumber;
        Query query = session.createQuery(hql);
        List<?> result = query.list();
        if(result.size() > 1) {
            throw new InvalidResultException(INVALID_USERS_VALUES + " " + accountNumber);
        }

        InternalAccount acc = (InternalAccount) result.get(0);
        BigDecimal currentBalance = acc.getBalance();
        acc.setBalance(currentBalance.add(amount));
        session.update(acc);
    }

    @Override
    public List<InternalAccount> getAll() {
        Session session = HibernateLifecycleUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from InternalAccount");
        List<?> result = query.list();
        List<InternalAccount> accounts = result.stream().map(n -> (InternalAccount) n).collect(Collectors.toList());
        session.close();

        return accounts;
    }
}
