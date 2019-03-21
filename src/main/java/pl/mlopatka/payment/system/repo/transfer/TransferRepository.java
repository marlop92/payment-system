package pl.mlopatka.payment.system.repo.transfer;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.entities.Transfer;

import java.util.List;

public interface TransferRepository {

    void saveTransfer(TransferCandidate transferCandidate, Session session);

    List<Transfer> getAll();
}
