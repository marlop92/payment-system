package pl.mlopatka.payment.system.controllers;

import pl.mlopatka.payment.system.model.TransferRequest;
import pl.mlopatka.payment.system.services.transfer.TransferService;
import pl.mlopatka.payment.system.validators.TransferValidator;

public class TransferController {

    private final TransferService transferService;
    private final TransferValidator transferValidator;

    public TransferController(final TransferService transferService, final TransferValidator transferValidator) {
        this.transferService = transferService;
        this.transferValidator = transferValidator;
    }

    //POST
    public void transferMoney(TransferRequest transferRequest) {
        transferValidator.validate(transferRequest);
        transferService.transferMoney(transferRequest);
    }
}
