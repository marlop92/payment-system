package pl.mlopatka.payment.system.services.transfer;

import pl.mlopatka.payment.system.model.requests.TransferRequest;

import java.time.LocalDateTime;

public interface TransferService {

    void transferMoney(TransferRequest transferRequest, LocalDateTime transferDate);
}
