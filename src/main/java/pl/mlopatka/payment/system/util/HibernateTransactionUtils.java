package pl.mlopatka.payment.system.util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.mlopatka.payment.system.exceptions.InvalidRestTransaction;
import pl.mlopatka.payment.system.exceptions.InvalidResultException;
import pl.mlopatka.payment.system.exceptions.InvalidTransactionState;
import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.services.transfer.TransferService;

import java.time.LocalDateTime;

public class HibernateTransactionUtils {

    private static final String INTERRUPTED_TRANSACTION = "Transaction interrupted: ";

    public static void runTransaction(final TransferService transferService, final TransferRequest transferRequest) {
        Session session = HibernateLifecycleUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            transferService.transferMoney(transferRequest, LocalDateTime.now(), session);
            tx.commit();
        } catch (InvalidResultException | InvalidTransactionState ex) {
            tx.rollback();
            throw new InvalidRestTransaction(INTERRUPTED_TRANSACTION + ex.getMessage());
        } catch (RuntimeException ex) {
            tx.rollback();
            throw new RuntimeException(INTERRUPTED_TRANSACTION + ex.getMessage());
        } finally {
            session.close();
        }
    }
}
