package pl.mlopatka.payment.system.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(final String message) {
        super(message);
    }
}
