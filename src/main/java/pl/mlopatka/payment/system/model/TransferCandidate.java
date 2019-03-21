package pl.mlopatka.payment.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;

public class TransferCandidate {

    private String senderAccount;
    private String receiverAccount;
    private String title;
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime transferDate;

    public TransferCandidate(final String senderAccount, final String receiverAccount, final String title,
                             final BigDecimal amount, final Currency currency, final LocalDateTime transferDate) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.transferDate = transferDate;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(final String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(final String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(final LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferCandidate that = (TransferCandidate) o;
        return Objects.equals(senderAccount, that.senderAccount) &&
                Objects.equals(receiverAccount, that.receiverAccount) &&
                Objects.equals(title, that.title) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transferDate, that.transferDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderAccount, receiverAccount, title, amount, transferDate);
    }
}
