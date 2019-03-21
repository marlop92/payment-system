package pl.mlopatka.payment.system.model;

import java.math.BigDecimal;

public class InternalAccountDto {

    private Long id;
    private Long cid;
    private String accountNumber;
    private BigDecimal balance;
    private String currency;

    public InternalAccountDto(final Long id, final Long cid, final String accountNumber, final BigDecimal balance,
                              final String currency) {
        this.id = id;
        this.cid = cid;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public Long getCid() {
        return cid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
