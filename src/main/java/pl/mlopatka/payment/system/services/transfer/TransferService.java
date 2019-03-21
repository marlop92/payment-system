package pl.mlopatka.payment.system.services.transfer;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.TransferDto;
import pl.mlopatka.payment.system.model.requests.TransferRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferService {

    void transferMoney(TransferRequest transferRequest, LocalDateTime transferDate, Session session);

    List<TransferDto> getAllTransfers();
}
