package club.myfpl.resources;

import club.myfpl.domain.Club;
import club.myfpl.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Saket
 * @since 23/04/17.
 */
@Component
@Path("clubs")
@Consumes("application/json")
@Produces("application/json")
public class ClubResource {

    private final ClubService clubService;

    @Autowired
    public ClubResource(ClubService clubService) {
        this.clubService = clubService;
    }

    @GET
    @Path("{id}")
    public Response fetchClub(@PathParam("id") Long id) {
        Club club = clubService.getClubRepository().findOne(id);
        if (club == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(id).build();
        }
        return Response.ok(club).build();
    }

    @GET
    public Response fetchClubs() {
        List<Club> clubs = clubService.getClubRepository().findAll();
        return Response.ok(clubs).build();
    }

}
