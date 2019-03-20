package pl.mlopatka.payment.system.controllers;

import pl.mlopatka.payment.system.model.requests.TransferRequest;
import pl.mlopatka.payment.system.services.transfer.TransferService;
import pl.mlopatka.payment.system.services.transfer.TransferServiceImpl;
import pl.mlopatka.payment.system.validators.TransferValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;


//TODO handle exceptions, add response statues, parse POST body (JACKSON needed), add tests
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
    @Consumes(MediaType.APPLICATION_JSON)
    public void transferMoney(final TransferRequest transferRequest) {
        transferValidator.validate(transferRequest);
        transferService.transferMoney(transferRequest, LocalDateTime.now());
    }

}
