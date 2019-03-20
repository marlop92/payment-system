package pl.mlopatka.payment.system.util;

public class ApplicationLifecycleUtils {

    public static void setUp() {
        HibernateLifecycleUtil.init();
    }

    public static void cleanUp(){
        HibernateLifecycleUtil.destroy();
    }

}
