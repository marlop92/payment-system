package pl.mlopatka.payment.system.services.transfer;

import pl.mlopatka.payment.system.model.TransferRequest;

public interface TransferService {

    void transferMoney(TransferRequest transferRequest);
}
