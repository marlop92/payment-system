package pl.mlopatka.payment.system.controllers;

import pl.mlopatka.payment.system.model.TransferDto;
import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.services.transfer.TransferService;
import pl.mlopatka.payment.system.services.transfer.TransferServiceImpl;
import pl.mlopatka.payment.system.util.HibernateTransactionUtils;
import pl.mlopatka.payment.system.validators.TransferValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("transfers")
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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferDto> getExternalAccounts() {
        return transferService.getAllTransfers();
    }



    @POST
    @Path("transfer")
    public void transferMoney(final TransferRequest transferRequest) {
        transferValidator.validate(transferRequest);
        HibernateTransactionUtils.runTransaction(transferService, transferRequest);
    }


}
