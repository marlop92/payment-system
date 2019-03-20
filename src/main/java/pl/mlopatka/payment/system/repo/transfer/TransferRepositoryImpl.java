package pl.mlopatka.payment.system.repo.transfer;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.entities.Transfer;

public class TransferRepositoryImpl implements TransferRepository {

    @Override
    public void saveTransfer(TransferCandidate transferCandidate, Session session) {
        Transfer transfer = new Transfer(transferCandidate.getSenderAccount(), transferCandidate.getReceiverAccount(),
                transferCandidate.getTitle(), transferCandidate.getAmount(), transferCandidate.getCurrency(),
                transferCandidate.getTransferDate());

        session.save(transfer);
    }
}
