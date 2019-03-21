package pl.mlopatka.payment.system.repo.transfer;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.entities.Transfer;
import pl.mlopatka.payment.system.util.HibernateLifecycleUtil;

import java.util.List;
import java.util.stream.Collectors;

public class TransferRepositoryImpl implements TransferRepository {

    @Override
    public void saveTransfer(TransferCandidate transferCandidate, Session session) {
        Transfer transfer = new Transfer(transferCandidate.getSenderAccount(), transferCandidate.getReceiverAccount(),
                transferCandidate.getTitle(), transferCandidate.getAmount(), transferCandidate.getCurrency(),
                transferCandidate.getTransferDate());

        session.save(transfer);
    }

    @Override
    public List<Transfer> getAll() {
        Session session = HibernateLifecycleUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Transfer");
        List<?> result = query.list();
        List<Transfer> accounts = result.stream().map(n -> (Transfer) n).collect(Collectors.toList());
        session.close();

        return accounts;
    }
}
