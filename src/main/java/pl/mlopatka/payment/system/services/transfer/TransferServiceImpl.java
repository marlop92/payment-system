package pl.mlopatka.payment.system.services.transfer;

import pl.mlopatka.payment.system.model.Customer;
import pl.mlopatka.payment.system.model.TransferCandidate;
import pl.mlopatka.payment.system.model.TransferRequest;
import pl.mlopatka.payment.system.repo.TransferRepository;
import pl.mlopatka.payment.system.services.customer.CustomerService;
import pl.mlopatka.payment.system.util.TransferUtil;

import javax.money.CurrencyUnit;

public class TransferServiceImpl implements TransferService {

    private final CustomerService customerService;
    private final TransferRepository transferRepository;

    public TransferServiceImpl(final CustomerService customerService, final TransferRepository transferRepository) {
        this.customerService = customerService;
        this.transferRepository = transferRepository;
    }

    public void transferMoney(final TransferRequest transferRequest) {
        //TODO implementation of get method should throw runtime exception when there will be no matching user
        //PRECONDITIONS
        Customer customer = customerService.getCustomer(transferRequest.getCid());
        CurrencyUnit currency = customerService.acceptCurrency(transferRequest.getCid(), transferRequest.getCurrency());
//        customerService.verifyMoneyCapability(transferRequest.getCid(), transferRequest.getAmount());
        customerService.verifyReceiverAccount(transferRequest.getReceiverAccount());

        TransferCandidate transferCandidate = TransferUtil.createTransferCandidate(customer, transferRequest);
        transferRepository.doMoneyTransfer(transferCandidate);
    }
}
