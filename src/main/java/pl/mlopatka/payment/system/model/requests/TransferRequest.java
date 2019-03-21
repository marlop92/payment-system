package pl.mlopatka.payment.system.model.requests;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferRequest implements Serializable {

    private int cid;
    private String receiverAccount;
    private String title;
    private BigDecimal amount;
    private String currency;

    public TransferRequest(final int cid, final String receiverAccount, final String title, final BigDecimal amount,
                           final String currency) {
        this.cid = cid;
        this.receiverAccount = receiverAccount;
        this.title = title;
        this.amount = amount;
        this.currency = currency;
    }

    public TransferRequest() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(final int cid) {
        this.cid = cid;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }
}
