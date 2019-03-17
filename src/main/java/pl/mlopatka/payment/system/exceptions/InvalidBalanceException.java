package pl.mlopatka.payment.system.exceptions;

public class InvalidBalanceException extends  RuntimeException {

    public InvalidBalanceException(String message) {
        super(message);
    }
}
