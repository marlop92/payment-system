package pl.mlopatka.payment.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
}
