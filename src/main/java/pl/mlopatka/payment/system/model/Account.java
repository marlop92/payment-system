package pl.mlopatka.payment.system.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Account {

    private String accountNumber;
    private BigDecimal balance;
    private Currency currency;

    public Account() {
    }

    public Account(final String accountNumber, final BigDecimal balance, final Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(currency, account.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, currency);
    }


}
