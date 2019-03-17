package pl.mlopatka.payment.system.exceptions;

public class MoneyTransferValidationException extends RuntimeException {

    public MoneyTransferValidationException(String message) {
        super(message);
    }
}
