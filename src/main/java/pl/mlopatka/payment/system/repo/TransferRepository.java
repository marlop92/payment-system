package pl.mlopatka.payment.system.repo;

import pl.mlopatka.payment.system.model.TransferCandidate;

public interface TransferRepository {

    void saveTransfer(TransferCandidate transferCandidate);
    void updateBalance(TransferCandidate transferCandidate);
}
