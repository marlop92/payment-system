package pl.mlopatka.payment.system.util;

import org.javamoney.moneta.Money;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;

import java.time.ZonedDateTime;

public class TransferUtil {

    public static TransferCandidate createTransferCandidate(
            final InternalAccount senderAccount,
            final ExternalAccount receiverAccount,
            final Money amount,
            final String title,
            final ZonedDateTime transactionDate
    ) {
        return new TransferCandidate(senderAccount.getAccountNumber(), receiverAccount.getAccountNumber(), title,
                amount, transactionDate);
    }
}
