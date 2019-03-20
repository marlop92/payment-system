package pl.mlopatka.payment.system.model.requests;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement
public class TransferRequest implements Serializable {

    private int cid;
    private String receiverAccount;
    private String title;
    private BigDecimal amount;
    private String currency;

    public TransferRequest(int cid, String receiverAccount, String title, BigDecimal amount, String currency) {
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

    public void setCid(int cid) {
        this.cid = cid;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
