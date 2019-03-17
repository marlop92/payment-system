package pl.mlopatka.payment.system.model;

import org.javamoney.moneta.Money;

import java.time.ZonedDateTime;
import java.util.Objects;

public class TransferCandidate {

    private String senderAccount;
    private String receiverAccount;
    private String title;
    private Money amount;
    private ZonedDateTime transferDate;

    public TransferCandidate(final String senderAccount, final String receiverAccount, final String title,
                             final Money amount, final ZonedDateTime transferDate) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.title = title;
        this.amount = amount;
        this.transferDate = transferDate;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(ZonedDateTime transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public boolean equals(Object o) {
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
