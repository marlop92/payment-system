package pl.mlopatka.payment.system.model;

import java.util.Objects;

public class ExternalAccountDto {

    private Long id;
    private String accountNumber;
    private String currency;

    public ExternalAccountDto(final Long id, final String accountNumber, final String currency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalAccountDto that = (ExternalAccountDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, currency);
    }
}
