package pl.mlopatka.payment.system.validators;

import pl.mlopatka.payment.system.model.requests.TransferRequest;

import java.math.BigDecimal;

public class TransferValidator {

    private static final int ACCOUNT_LENGTH = 16;
    private static final String INVALID_ACCOUNT_FORMAT = "Destination account should contain " + ACCOUNT_LENGTH + "digits";
    private static final int TITLE_MAX_LENGTH = 256;
    private static final String INVALID_TITLE_LENGTH = "Money transfer title length can be only from 0 to " +
            TITLE_MAX_LENGTH;
    private static final int MIN_VALUE = 0;
    private static final String INVALID_AMOUNT = "Money transfer amount have to be grater than " + MIN_VALUE;
    private static final int EQUALITY = 0;
    private static final String ONE_OR_MORE_DIGITS = "[0-9]+";
    private static final String INVALID_CURRENCY = "Found not existing currency";
    public static final int CURRENCY_CODE_LENGTH = 3;

    public void validate(TransferRequest transferRequest) {
        validateAmount(transferRequest.getAmount());
        validateCurrency(transferRequest.getCurrency());
        validateReceiverAccount(transferRequest.getReceiverAccount());
        validateTitle(transferRequest.getTitle());
    }

    private void validateTitle(final String title) {
        if (title == null || title.length() > TITLE_MAX_LENGTH) {
            throw new IllegalArgumentException(INVALID_TITLE_LENGTH);
        }
    }

    private void validateReceiverAccount(final String receiverAccount) {
        if (receiverAccount == null || receiverAccount.length() != ACCOUNT_LENGTH
                || !receiverAccount.matches(ONE_OR_MORE_DIGITS)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT_FORMAT);
        }
    }

    private void validateCurrency(final String currency) {
        if (currency == null || currency.length() != CURRENCY_CODE_LENGTH) {
            throw new IllegalArgumentException(INVALID_CURRENCY);
        }
    }

    private void validateAmount(final BigDecimal amount) {
        if (amount == null || amount.compareTo(new BigDecimal(MIN_VALUE)) <= EQUALITY) {
            throw new IllegalArgumentException(INVALID_AMOUNT);
        }
    }

}
