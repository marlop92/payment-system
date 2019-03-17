package pl.mlopatka.payment.system.services.account.external;

import pl.mlopatka.payment.system.model.entities.ExternalAccount;

import java.util.Optional;

public interface ExternalAccountService {

    Optional<ExternalAccount> findAccount(String accountNumber, String currency);
}
