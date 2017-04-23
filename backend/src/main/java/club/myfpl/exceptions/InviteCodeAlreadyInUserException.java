package club.myfpl.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Saket
 * @since 23/04/17.
 */
public class InviteCodeAlreadyInUserException extends Exception implements ExceptionMapper<InviteCodeAlreadyInUserException> {

    public InviteCodeAlreadyInUserException(String inviteCode) {
        super(inviteCode);
    }

    @Override
    public Response toResponse(InviteCodeAlreadyInUserException exception) {
        return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()).build();
    }

}
