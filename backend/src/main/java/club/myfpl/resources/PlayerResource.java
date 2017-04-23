package club.myfpl.resources;

import club.myfpl.domain.Player;
import club.myfpl.services.PlayerService;
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
@Path("players")
@Consumes("application/json")
@Produces("application/json")
public class PlayerResource {

    private final PlayerService playerService;

    @Autowired
    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GET
    @Path("{id}")
    public Response fetchPlayer(@PathParam("id") Long id) {
        Player player = playerService.getPlayerRepository().findOne(id);
        if (player == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(id).build();
        }
        return Response.ok(player).build();
    }

    @GET
    public Response fetchAllPlayers() {
        List<Player> players = playerService.getPlayerRepository().findAll();
        return Response.ok(players).build();
    }

}
