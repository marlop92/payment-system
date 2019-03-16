package pl.mlopatka.payment.system.services.customer;

import org.javamoney.moneta.Money;
import pl.mlopatka.payment.system.model.Customer;

import javax.money.CurrencyUnit;

public interface CustomerService {

    Customer getCustomer(int cid);
    CurrencyUnit acceptCurrency(int cid, String currency);
    void verifyMoneyCapability(int cid, Money amount);
    void verifyReceiverAccount(String receiverAccount);
}
