package club.myfpl.auth;

import club.myfpl.model.User;
import club.myfpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * User: Saket
 * Date: 10/01/17
 * Time: 2:47 PM
 */
@Component
@Path("/login")
public class AuthEndpoint {
    private final UserService userService;

    @Autowired
    public AuthEndpoint(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticate(@FormParam("username") String username, @FormParam("password") String password, @Context HttpServletRequest request) {
        Optional<User> user = doAuthenticate(username, password);
        if (user.isPresent()) {
            request.getSession(true).setAttribute("user", user.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private Optional<User> doAuthenticate(String email, String password) {
        User user = userService.fetchUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
