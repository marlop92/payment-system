package pl.mlopatka.payment.system.exceptions;

public class InvalidResultException extends RuntimeException {

    public InvalidResultException(String message) {
        super(message);
    }
}
