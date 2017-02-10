package club.myfpl.resources;

import club.myfpl.resources.dto.UserDTO;
import club.myfpl.model.User;
import club.myfpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * User: Saket
 * Date: 08/02/17
 * Time: 5:50 PM
 */
@Component
@Path("user")
public class UserResource {
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("create")
    public Response createUser(UserDTO userDTO) {
        User user = userService.createUser(userDTO.toUser());
        return Response.ok(user.getUserId()).build();
    }

    @POST
    @Path("update/{userId}")
    public Response updateUser(@PathParam("userId") long userId, UserDTO userDTO) {
        User user = userService.updateUser(userId, userDTO.toUser());
        return Response.ok(user.getUserId()).build();
    }

    @GET
    @Path("fetch/{userId}")
    public Response fetchUser(@PathParam("userId") long userId) {
        return Response.ok(userService.fetchUser(userId)).build();
    }
}
