package pl.mlopatka.payment.system.model.entities;

import javax.money.CurrencyUnit;

public class ExternalAccount {

    private int id;
    private String accountNumber;
    private CurrencyUnit currency;

    public ExternalAccount(int id, String accountNumber, CurrencyUnit currency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyUnit currency) {
        this.currency = currency;
    }
}
