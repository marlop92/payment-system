package pl.mlopatka.payment.system.services.account.internal;

import pl.mlopatka.payment.system.model.entities.InternalAccount;

import java.util.Optional;

public interface InternalAccountService {

    Optional<InternalAccount> findAccount(int cid, String currency);
}
