package club.myfpl.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Saket
 * @since 23/04/17.
 */
public class LeagueNotFoundException extends Exception implements ExceptionMapper<LeagueNotFoundException> {

    public LeagueNotFoundException(String leagueId) {
        super(leagueId);
    }

    @Override
    public Response toResponse(LeagueNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }

}
