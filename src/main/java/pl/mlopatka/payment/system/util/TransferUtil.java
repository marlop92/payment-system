package pl.mlopatka.payment.system.util;

import pl.mlopatka.payment.system.model.Account;
import pl.mlopatka.payment.system.model.TransferCandidate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferUtil {

    public static TransferCandidate createTransferCandidate(
            final Account senderAccount,
            final String receiverAccount,
            final String title,
            final BigDecimal amount,
            final LocalDateTime transactionDate
    ) {
        return new TransferCandidate(senderAccount.getAccountNumber(), receiverAccount, title,
                amount, senderAccount.getCurrency(), transactionDate);
    }
}
