package pl.mlopatka.payment.system.validators;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;
import pl.mlopatka.payment.system.model.requests.TransferRequest;

import java.math.BigDecimal;

//TODO Maybe it's worth to check exception msg when it is thrown (as some of tests can pass when error appear in any place)
public class TransferValidatorTest {

    TransferValidator transferValidator = new TransferValidator();

    @Test
    public void validRequestShouldNotThrowException() {
        //given
        TransferRequest transferRequest = new TransferRequest(1, "1234567812345678",
                "title", new BigDecimal(10.0), "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - no exception

    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidAmountShouldThrowException() {
        //given
        BigDecimal amount = new BigDecimal(-1.0);
        TransferRequest transferRequest = new TransferRequest(1, "1234567812345678",
                "title", amount, "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyAmountShouldThrowException() {
        //given
        BigDecimal amount = null;
        TransferRequest transferRequest = new TransferRequest(1, "1234567812345678",
                "title", amount, "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidCurrencyShouldThrowException() {
        //given
        String currency = "INVALID_CURRENCY";
        TransferRequest transferRequest = new TransferRequest(1, "1234567812345678",
                "title", new BigDecimal(10.0), currency);

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyCurrencyShouldThrowException() {
        //given
        String currency = "";
        TransferRequest transferRequest = new TransferRequest(1, "1234567812345678",
                "title", new BigDecimal(10.0), currency);

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidAccountFormatShouldThrowException() {
        //given
        String accountNr = "Account412341234";
        TransferRequest transferRequest = new TransferRequest(1, accountNr, "title", new BigDecimal(10.0), "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidAccountLengthShouldThrowException() {
        //given
        String accountNr = "1234";
        TransferRequest transferRequest = new TransferRequest(1, accountNr, "title", new BigDecimal(10.0), "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyAccountFormatShouldThrowException() {
        //given
        String accountNr = null;
        TransferRequest transferRequest = new TransferRequest(1, accountNr, "title", new BigDecimal(10.0), "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTitleShouldThrowException() {
        //given
        String title = generateRandomString(257);
        TransferRequest transferRequest = new TransferRequest(1, "1234567812345678", title,
                new BigDecimal(10.0), "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyTitleShouldThrowException() {
        //given
        String title = null;
        TransferRequest transferRequest = new TransferRequest(1, "1234567812345678", title,
                new BigDecimal(10.0), "PLN");

        //when
        transferValidator.validate(transferRequest);

        //than - expect exception
    }

    private String generateRandomString(int length) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();

        return generator.generate(length);
    }
}