package pl.mlopatka.payment.system.services.account;

import org.hibernate.Session;
import pl.mlopatka.payment.system.model.Account;
import pl.mlopatka.payment.system.model.entities.ExternalAccount;
import pl.mlopatka.payment.system.model.entities.InternalAccount;
import pl.mlopatka.payment.system.repo.account.external.ExternalAccountRepo;
import pl.mlopatka.payment.system.repo.account.external.ExternalAccountRepoImpl;
import pl.mlopatka.payment.system.repo.account.internal.InternalAccountRepo;
import pl.mlopatka.payment.system.repo.account.internal.InternalAccountRepoImpl;
import pl.mlopatka.payment.system.util.AccountType;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final InternalAccountRepo internalAccountRepo;
    private final ExternalAccountRepo externalAccountRepo;

    public AccountServiceImpl() {
        this.externalAccountRepo = new ExternalAccountRepoImpl();
        this.internalAccountRepo = new InternalAccountRepoImpl();
    }

    public AccountServiceImpl(InternalAccountRepo internalAccountRepo, ExternalAccountRepo externalAccountRepo) {
        this.internalAccountRepo = internalAccountRepo;
        this.externalAccountRepo = externalAccountRepo;
    }

    @Override
    public Optional<Account> findInternalAccount(final int id, final String currency, final Session session) {
        Optional<InternalAccount> account = internalAccountRepo.findAccount(id, currency, session);
        Optional<Account> simpleAccount = account
                .map(n -> new Account(n.getAccountNumber(), n.getBalance(), n.getCurrency()));
        return simpleAccount;
    }

    @Override
    public AccountType checkAccountType(final String accountNumber, final String currency, final Session session) {
        Optional<InternalAccount> account = internalAccountRepo.findAccount(accountNumber, currency, session);
        if (account.isPresent()) {
            return AccountType.INTERNAL;
        }

        Optional<ExternalAccount> externalAccount = externalAccountRepo.findAccount(accountNumber, currency, session);
        return externalAccount.map(n -> AccountType.EXTERNAL).orElse(AccountType.NONE);
    }

    @Override
    public void updateAccount(final String accountNumber, final BigDecimal amount, final Session session) {
        internalAccountRepo.updateAccountBalance(accountNumber, amount, session);
    }

    @Override
    public void externalTransfer(final String accountNumber, final BigDecimal amount) {
        //transfer money to third party
    }
}
