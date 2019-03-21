package pl.mlopatka.payment.system.model;

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
}