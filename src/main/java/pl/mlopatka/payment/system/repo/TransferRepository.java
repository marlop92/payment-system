package pl.mlopatka.payment.system.repo;

import pl.mlopatka.payment.system.model.TransferCandidate;

public interface TransferRepository {

    void doMoneyTransfer(TransferCandidate transferCandidate);
}
