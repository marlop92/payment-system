package pl.mlopatka.payment.system.exceptions;

public class InvalidTransactionState extends RuntimeException {

    public InvalidTransactionState(String message) {
        super(message);
    }
}
