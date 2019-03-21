package pl.mlopatka.payment.system.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidRestTransaction extends RuntimeException implements ExceptionMapper<InvalidRestTransaction> {

    public InvalidRestTransaction() {

    }

    public InvalidRestTransaction(final String message) {
        super(message);
    }

    @Override
    public Response toResponse(final InvalidRestTransaction invalidRestTransaction) {
        return Response.status(Response.Status.BAD_REQUEST).entity(invalidRestTransaction.getMessage()).build();
    }
}
