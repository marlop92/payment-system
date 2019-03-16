package pl.mlopatka.payment.system.services.money;

import javax.money.MonetaryCurrencies;
import javax.money.MonetaryException;

/**
 * Maybe instead of catching MonetaryException I can handle this with exception handler
 */
public class CurrencyServiceImpl implements CurrencyService {

    public static final String INVALID_CURRENCY_VALUE = "Invalid currency value";

    @Override
    public void validateCurrency(String currency) {
        if(currency == null) {
            throw new RuntimeException(INVALID_CURRENCY_VALUE);
        }

        try {
            MonetaryCurrencies.getCurrencies(currency);
        } catch (MonetaryException monetaryException) {
            throw new RuntimeException(INVALID_CURRENCY_VALUE);
        }
    }
}
