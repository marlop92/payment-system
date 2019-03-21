package pl.mlopatka.payment.system.services.account;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.mlopatka.payment.system.repo.account.external.ExternalAccountRepo;
import pl.mlopatka.payment.system.repo.account.internal.InternalAccountRepo;

public class AccountServiceTest {

    @Mock
    InternalAccountRepo internalAccountRepo;

    @Mock
    ExternalAccountRepo externalAccountRepo;

    @InjectMocks
    AccountService accountService;

    @Before
    public void setOff() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {

    }

}