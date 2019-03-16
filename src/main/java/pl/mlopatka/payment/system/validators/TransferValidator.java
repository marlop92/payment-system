package pl.mlopatka.payment.system.validators;

import pl.mlopatka.payment.system.model.TransferRequest;
import pl.mlopatka.payment.system.services.money.CurrencyService;

import java.math.BigDecimal;

public class TransferValidator {

    private static final int ACCOUNT_LENGTH = 16;
    private static final int TITLE_MAX_LENGTH = 256;

    private final CurrencyService currencyService;

    public TransferValidator(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void validate(TransferRequest transferRequest) {
        validateAmount(transferRequest.getAmount());
        validateCurrency(transferRequest.getCurrency());
        validateReceiverAccount(transferRequest.getReceiverAccount());
        validateTitle(transferRequest.getTitle());
    }

    private void validateTitle(String title) {
        if(title == null || title.length() > TITLE_MAX_LENGTH) {
            throw new RuntimeException("TransferCandidate title cannot be more than " + TITLE_MAX_LENGTH + " characters");
        }
    }

    private void validateReceiverAccount(String receiverAccount) {
        //TODO check if contains digits only
        if(receiverAccount == null || receiverAccount.length() == ACCOUNT_LENGTH) {
            throw new RuntimeException("Account should have " + ACCOUNT_LENGTH + " digits");
        }
    }

    private void validateCurrency(String currency) {
        currencyService.validateCurrency(currency);
    }

    private void validateAmount(BigDecimal amount) {
        if(amount == null || amount.compareTo(new BigDecimal(0)) <= 0) {
            throw new RuntimeException("User can transfer only positive money amount");
        }
    }


}
