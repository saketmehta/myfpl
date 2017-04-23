package club.myfpl.resources;

import club.myfpl.domain.League;
import club.myfpl.exceptions.*;
import club.myfpl.resources.dto.CreateLeagueDTO;
import club.myfpl.resources.dto.UpdateLeagueDTO;
import club.myfpl.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: Saket
 * Date: 12/02/17
 * Time: 5:25 PM
 */
@Component
@Path("users/{userId}/leagues")
public class LeagueResource {

    private final LeagueService leagueService;

    @Autowired
    public LeagueResource(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GET
    public Response fetchLeagues(@PathParam("userId") String userId) {
        List<League> leagues = this.leagueService.geLeagueRepository().findByUsersContaining(userId);
        return Response.ok(leagues).build();
    }

    @GET
    @Path("{id}")
    public Response fetchLeague(@PathParam("userId") String userId, @PathParam("id") String leagueId) throws LeagueNotFoundException {
        League league = this.leagueService.geLeagueRepository().findOne(leagueId);
        if (league == null) {
            throw new LeagueNotFoundException("Cannot find league with ID: " + leagueId);
        }
        return Response.ok(league).build();
    }

    @POST
    public Response createLeague(CreateLeagueDTO createLeagueDTO, @PathParam("userId") String userId) throws InviteCodeAlreadyInUserException {
        createLeagueDTO.setAdminUserId(userId);
        League league = leagueService.createLeague(createLeagueDTO);
        return Response.ok(league).build();
    }

    @PUT
    @Path("{id}")
    public Response updateLeague(UpdateLeagueDTO updateLeagueDTO, @PathParam("userId") String userId) throws LeagueNotFoundException {
        League league = leagueService.updateLeague(updateLeagueDTO);
        return Response.ok(league).build();
    }

    @POST
    @Path("{inviteCode}/join")
    public Response joinLeague(@PathParam("userId") String userId, @PathParam("inviteCode") String inviteCode) throws LeagueIsLockedException, LeagueCapacityException {
        League league = leagueService.addUserToLeague(inviteCode, userId);
        return Response.ok(league).build();
    }

    @POST
    @Path("{id}/leave")
    public Response leaveLeague(@PathParam("userId") String userId, @PathParam("id") String leagueId) throws LeagueIsLockedException, LeagueCapacityException, LeagueUserException {
        League league = leagueService.removeUserFromLeague(leagueId, userId);
        return Response.ok(league).build();
    }

    @POST
    @Path("{id}/lock")
    public Response lockLeague(@PathParam("id") String leagueId) throws LeagueNotFoundException, LeagueCapacityException {
        League league = leagueService.lockLeague(leagueId);
        return Response.ok(league).build();
    }

    @POST
    @Path("{id}/unlock")
    public Response unlockLeague(@PathParam("id") String leagueId) throws LeagueNotFoundException {
        League league = leagueService.unlockLeague(leagueId);
        return Response.ok(league).build();
    }

}
