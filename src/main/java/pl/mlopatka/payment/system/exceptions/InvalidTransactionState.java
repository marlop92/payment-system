package pl.mlopatka.payment.system.exceptions;

public class InvalidTransactionState extends RuntimeException {

    public InvalidTransactionState(final String message) {
        super(message);
    }
}
