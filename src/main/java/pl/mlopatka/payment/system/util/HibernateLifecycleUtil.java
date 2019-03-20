package pl.mlopatka.payment.system.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import pl.mlopatka.payment.system.model.entities.Customer;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;
import pl.mlopatka.payment.system.model.entities.Transfer;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class HibernateLifecycleUtil {

    public static final String PLN = "PLN";
    public static final String EUR = "EUR";
    private static SessionFactory sessionFactory;

    public static void init() {
        Configuration conf = getConfiguration();
        sessionFactory = conf.buildSessionFactory();
    }

    public static void initDb() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            initData(session);
            transaction.commit();
        } catch (RuntimeException ex) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    private static void initData(final Session session) {
        saveCustomerWithTwoAccounts(session, "1234567890123456", "9876543210987654",
                new BigDecimal(1000.00), new BigDecimal(300.00));
        saveCustomerWithTwoAccounts(session, "2200000077777777", "1600000077777777",
                new BigDecimal(5999.99), new BigDecimal(0.00));
        saveCustomerWithTwoAccounts(session, "1111111111111111", "2222222222222222",
                new BigDecimal(50.00), new BigDecimal(12000.00));
        saveCustomerWithSingleAccount(session, "9900000000000000", new BigDecimal(127.00));
        saveCustomerWithSingleAccount(session, "3300000000000000", new BigDecimal(4.00));

        saveExternalAccount(session, "9900990099009900", PLN);
        saveExternalAccount(session, "1212121212121212", EUR);
        saveExternalAccount(session, "2121212121212121", PLN);
        saveExternalAccount(session, "3333333333333333", EUR);
        saveExternalAccount(session, "9009900990099009", PLN);
    }

    private static void saveExternalAccount(final Session session, final String accountNumber,
                                            final String currencyCode) {
        ExternalAccount externalAccount = new ExternalAccount(accountNumber, Currency.getInstance(currencyCode));
        session.save(externalAccount);
    }

    private static void saveCustomerWithTwoAccounts( final Session session, final String firstAccount,
                                                     final String secAccount, final BigDecimal firstBalance,
                                                     final BigDecimal secBalance) {
        Customer customer = new Customer();
        InternalAccount customerPln = createInternalAccount(firstAccount, firstBalance, customer, PLN);
        InternalAccount customerEur = new InternalAccount(customer, secAccount, secBalance,
                Currency.getInstance(EUR));

        Set<InternalAccount> accounts = new HashSet<>();
        accounts.add(customerPln);
        accounts.add(customerEur);

        session.save(customer);
        session.save(customerPln);
        session.save(customerEur);
    }

    private static void saveCustomerWithSingleAccount( final Session session, final String firstAccount,
                                                     final BigDecimal firstBalance) {
        Customer customer = new Customer();
        InternalAccount customerPln = createInternalAccount(firstAccount, firstBalance, customer, "PLN");
        Set<InternalAccount> accounts = new HashSet<>();
        accounts.add(customerPln);

        session.save(customer);
        session.save(customerPln);
    }

    private static InternalAccount createInternalAccount(String firstAccount, BigDecimal firstBalance, Customer customer, String pln) {
        return new InternalAccount(customer, firstAccount, firstBalance,
                Currency.getInstance(pln));
    }

    public static void destroy() {
        sessionFactory.close();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration conf = new Configuration();
        Properties properties = getProperties();
        conf.setProperties(properties);
        conf.addAnnotatedClass(Customer.class);
        conf.addAnnotatedClass(Transfer.class);
        conf.addAnnotatedClass(ExternalAccount.class);
        conf.addAnnotatedClass(InternalAccount.class);
        return conf;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        properties.setProperty(Environment.DRIVER, "org.h2.Driver");
        properties.setProperty(Environment.USER, "sa");
        properties.setProperty(Environment.PASS, "");
        properties.setProperty(Environment.URL, "jdbc:h2:~/test");
        return properties;
    }
}
