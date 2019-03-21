package pl.mlopatka.payment.system.repo.transfer;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.TransferCandidate;

public interface TransferRepository {

    void saveTransfer(TransferCandidate transferCandidate, Session session);
}
