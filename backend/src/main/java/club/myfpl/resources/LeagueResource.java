package club.myfpl.resources;

import club.myfpl.model.League;
import club.myfpl.model.User;
import club.myfpl.services.LeagueService;
import club.myfpl.utils.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: Saket
 * Date: 12/02/17
 * Time: 5:25 PM
 */
@Component
@Path("leagues")
public class LeagueResource {

    private final LeagueService leagueService;

    @Autowired
    public LeagueResource(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GET
    @Path("/")
    public Response getLeagues(@Context HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionConstants.USER);
        List<League> leagues = this.leagueService.fetchLeaguesForUser(user.getUserId());
        return Response.ok(leagues).build();
    }
}
