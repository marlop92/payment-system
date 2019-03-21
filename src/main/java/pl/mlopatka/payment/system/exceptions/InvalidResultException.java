package pl.mlopatka.payment.system.exceptions;

public class InvalidResultException extends RuntimeException {

    public InvalidResultException(final String message) {
        super(message);
    }
}
