package club.myfpl.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Saket
 * @since 23/04/17.
 */
public class LeagueUserException extends Exception implements ExceptionMapper<LeagueUserException> {

    public LeagueUserException(String leagueName) {
        super(leagueName);
    }

    @Override
    public Response toResponse(LeagueUserException exception) {
        return Response.status(Response.Status.CONFLICT).build();
    }

}
