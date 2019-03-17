package pl.mlopatka.payment.system.model.entities;

import org.javamoney.moneta.Money;

public class InternalAccount {

    private int id;
    private Customer customer;
    private String accountNumber;
    private Money balance;

    public InternalAccount(int id, Customer customer, String accountNumber, Money balance) {
        this.id = id;
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
