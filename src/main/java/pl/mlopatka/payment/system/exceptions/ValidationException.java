package pl.mlopatka.payment.system.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationException extends RuntimeException implements ExceptionMapper<ValidationException> {

    public ValidationException() {
    }

    public ValidationException(final String message) {
        super(message);
    }

    @Override
    public Response toResponse(final ValidationException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
