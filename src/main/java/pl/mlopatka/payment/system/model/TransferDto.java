package pl.mlopatka.payment.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransferDto {

    private Long id;
    private String senderAccount;
    private String receiverAccount;
    private String title;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime transferDate;

    public TransferDto(final Long id, final String senderAccount, final String receiverAccount, final String title,
                       final BigDecimal amount,final String currency, final LocalDateTime transferDate) {
        this.id = id;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.transferDate = transferDate;
    }

    public Long getId() {
        return id;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferDto that = (TransferDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(senderAccount, that.senderAccount) &&
                Objects.equals(receiverAccount, that.receiverAccount) &&
                Objects.equals(title, that.title) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(transferDate, that.transferDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderAccount, receiverAccount, title, amount, currency, transferDate);
    }
}
