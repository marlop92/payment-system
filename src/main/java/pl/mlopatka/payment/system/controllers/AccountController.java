package pl.mlopatka.payment.system.controllers;

import pl.mlopatka.payment.system.model.ExternalAccountDto;
import pl.mlopatka.payment.system.model.InternalAccountDto;
import pl.mlopatka.payment.system.services.account.AccountService;
import pl.mlopatka.payment.system.services.account.AccountServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController() {
        accountService = new AccountServiceImpl();
    }

    @GET
    @Path("external")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExternalAccountDto> getExternalAccounts() {
        return accountService.getAllExternals();
    }

    @GET
    @Path("internal")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InternalAccountDto> getInternalAccounts() {
        return accountService.getAllInternals();
    }
}


