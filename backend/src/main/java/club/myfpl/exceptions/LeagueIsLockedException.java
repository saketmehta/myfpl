package club.myfpl.exceptions;

import club.myfpl.domain.League;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Saket
 * @since 23/04/17.
 */
public class LeagueIsLockedException extends Exception implements ExceptionMapper<LeagueIsLockedException> {

    public LeagueIsLockedException(String msg) {
        super(msg);
    }

    @Override
    public Response toResponse(LeagueIsLockedException exception) {
        return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()).build();
    }

}
