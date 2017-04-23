package club.myfpl.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * User: Saket
 * Date: 11/02/17
 * Time: 7:18 PM
 */
@Provider
public class UserNotFoundException extends Exception implements ExceptionMapper<UserNotFoundException> {

    public UserNotFoundException(String id) {
        super(id);
    }

    @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }

}
