package pl.mlopatka.payment.system.services.money;

import java.math.BigDecimal;

public interface CurrencyService {

    void validateCurrency(String currency);
    void verifyAmount(BigDecimal amount);
}
