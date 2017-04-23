package club.myfpl.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * User: Saket
 * Date: 11/02/17
 * Time: 7:12 PM
 */
@Provider
public class EmailAlreadyInUseException extends Exception implements ExceptionMapper<EmailAlreadyInUseException> {

    public EmailAlreadyInUseException(String email) {
        super(email);
    }

    @Override
    public Response toResponse(EmailAlreadyInUseException exception) {
        return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()).build();
    }

}
