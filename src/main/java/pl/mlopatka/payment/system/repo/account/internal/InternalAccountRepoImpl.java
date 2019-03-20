package pl.mlopatka.payment.system.repo.account.internal;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.mlopatka.payment.system.model.entities.InternalAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InternalAccountRepoImpl implements InternalAccountRepo {

    @Override
    public Optional<InternalAccount> findAccount(int cid, String currency, Session session) {
        String hql = "from InternalAccount as account where account.customer.id = " + cid + " and account.currency like "
                + "'" + currency + "'";
        Query query = session.createQuery(hql);
        List result = query.list();
        if(result.size() > 1) {
            throw new RuntimeException("There should be only one customer with cid " + cid);
        }

        return Optional.ofNullable((InternalAccount) result.get(0));
    }

    @Override
    public Optional<InternalAccount> findAccount(String accountNumber, String currency, Session session) {
        String hql = "from InternalAccount as account where account.accountNumber = " + accountNumber + " and account.currency like "
                + currency;
        Query query = session.createQuery(hql);
        List result = query.list();
        if(result.size() > 1) {
            throw new RuntimeException("There should be only one customer with accountNumber " + accountNumber);
        }

        return Optional.ofNullable((InternalAccount) result.get(0));
    }

    @Override
    public void updateAccountBalance(String accountNumber, BigDecimal amount, Session session) {
        String hql = "from InternalAccount as account where account.accountNumber = " + accountNumber;
        Query query = session.createQuery(hql);
        List result = query.list();
        if(result.size() > 1) {
            throw new RuntimeException("There should be only one customer with accountNumber " + accountNumber);
        }

        InternalAccount acc = (InternalAccount) result.get(0);
        BigDecimal currentBalance = acc.getBalance();
        acc.setBalance(currentBalance.add(amount));
        session.update(acc);
    }
}
