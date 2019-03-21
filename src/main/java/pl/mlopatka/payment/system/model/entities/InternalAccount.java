package pl.mlopatka.payment.system.model.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Table(name = "internal_accounts")
@Access(AccessType.FIELD)
public class InternalAccount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gen")
    @SequenceGenerator(name = "id_seq_gen", sequenceName = "id_seq")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid", nullable = false)
    private Customer customer;

    @Column(name = "account_number", unique = true, updatable = false, nullable = false)
    private String accountNumber;

    @Column(name = "amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    public InternalAccount() {
    }

    public InternalAccount(Customer customer, String accountNumber, BigDecimal balance, Currency currency) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
