package club.myfpl.resources;

import club.myfpl.model.League;
import club.myfpl.model.User;
import club.myfpl.resources.dto.CreateLeagueDTO;
import club.myfpl.services.LeagueService;
import club.myfpl.utils.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

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
    public Response getLeagues(@Context HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionConstants.USER);
        List<League> leagues = this.leagueService.fetchLeaguesForUser(user.getUserId());
        return Response.ok(leagues).build();
    }

    @POST
    @Path("/create")
    public Response createLeague(CreateLeagueDTO createLeagueDTO, @Context HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionConstants.USER);
        League league = new League();
        league.setAdminUserId(user.getUserId());
        league.setName(createLeagueDTO.name);
        league.setCapacity(createLeagueDTO.capacity);
        league.setInviteCode(createLeagueDTO.inviteCode);
        league.addUser(user.getUserId());
        leagueService.createOrUpdateLeague(league);
        return Response.ok(league).build();
    }

    @POST
    @Path("/join")
    public Response joinLeague(String inviteCode, @Context HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionConstants.USER);
        Optional<League> league = leagueService.addUserToLeague(inviteCode, user.getUserId());
        if (league.isPresent()) {
            return Response.ok(league.get()).build();
        } else {
            return Response.notModified().build();
        }
    }
}
