package club.myfpl.exceptions;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Saket
 * @since 23/04/17.
 */
public class LeagueCapacityException extends Exception implements ExceptionMapper<LeagueCapacityException> {

    public LeagueCapacityException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(LeagueCapacityException exception) {
        return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()).build();
    }

}
