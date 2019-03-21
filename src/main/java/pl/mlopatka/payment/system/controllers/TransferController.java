package pl.mlopatka.payment.system.controllers;

import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.services.transfer.TransferService;
import pl.mlopatka.payment.system.services.transfer.TransferServiceImpl;
import pl.mlopatka.payment.system.util.HibernateTransactionUtils;
import pl.mlopatka.payment.system.validators.TransferValidator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("transaction")
public class TransferController {

    private final TransferService transferService;
    private final TransferValidator transferValidator;

    public TransferController(){
        transferService = new TransferServiceImpl();
        transferValidator = new TransferValidator();
    }

    public TransferController(final TransferService transferService, final TransferValidator transferValidator) {
        this.transferService = transferService;
        this.transferValidator = transferValidator;
    }

    @POST
    public void transferMoney(TransferRequest transferRequest) {
        transferValidator.validate(transferRequest);
        HibernateTransactionUtils.runTransaction(transferService, transferRequest);
    }


}
