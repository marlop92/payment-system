package pl.mlopatka.payment.system.model;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalAccountDto that = (InternalAccountDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cid, accountNumber, balance, currency);
    }
}
