package pl.mlopatka.payment.system.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import pl.mlopatka.payment.system.model.entities.Customer;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;
import pl.mlopatka.payment.system.model.entities.Transfer;

import java.util.Properties;

public class HibernateLifecycleUtil {

    private static SessionFactory sessionFactory;

    public static void init() {
        Configuration conf = getConfiguration();
        sessionFactory = conf.buildSessionFactory();
    }

    public static void destroy(){
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
        properties.setProperty(Environment.HBM2DDL_AUTO,"create-drop");
        properties.setProperty(Environment.DRIVER, "org.h2.Driver");
        properties.setProperty(Environment.USER, "sa");
        properties.setProperty(Environment.PASS, "");
        properties.setProperty(Environment.URL, "jdbc:h2:~/test");
        return properties;
    }
}
