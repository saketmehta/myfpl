package club.myfpl.resources;

import club.myfpl.exceptions.EmailAlreadyInUseException;
import club.myfpl.exceptions.UserNotFoundException;
import club.myfpl.domain.User;
import club.myfpl.resources.dto.CreateUserDTO;
import club.myfpl.resources.dto.UpdateUserDTO;
import club.myfpl.resources.dto.UserCredentialsDTO;
import club.myfpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author Saket
 * @since 08/02/17
 */
@Component
@Path("users")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("{id}")
    public Response fetchUser(@PathParam("id") String id) {
        User user = userService.getUserRepository().findOne(id);
        user.setPassword(null);
        return Response.ok(user).build();
    }

    @POST
    public Response createUser(CreateUserDTO createUserDTO, @Context UriInfo uriInfo) throws EmailAlreadyInUseException {
        User user = userService.createUser(createUserDTO);

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path(user.getId()).build();

        return Response.created(uri).entity(user).build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") String id, UpdateUserDTO updateUserDTO) throws UserNotFoundException, EmailAlreadyInUseException {
        updateUserDTO.setId(id);
        User user = userService.updateUser(updateUserDTO);

        return Response.ok(user).build();
    }

    @POST
    @Path("{id}/change-password")
    public Response changePassword(@PathParam("id") String id, UserCredentialsDTO userCredentialsDTO) {
        userCredentialsDTO.setId(id);
        boolean updated = userService.updatePassword(userCredentialsDTO);
        if (updated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
