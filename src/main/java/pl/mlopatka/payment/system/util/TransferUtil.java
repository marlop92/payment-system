package pl.mlopatka.payment.system.util;

import org.javamoney.moneta.Money;
import pl.mlopatka.payment.system.model.Customer;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.TransferRequest;

import java.time.ZonedDateTime;

public class TransferUtil {

    public static TransferCandidate createTransferCandidate(final Customer customer, final TransferRequest transferRequest) {
        ZonedDateTime now = ZonedDateTime.now();
        Money amount = Money.of(transferRequest.getAmount(), transferRequest.getCurrency());

        return new TransferCandidate(customer.getAccountNr(), transferRequest.getReceiverAccount(),
                transferRequest.getTitle(), amount, now);
    }
}
