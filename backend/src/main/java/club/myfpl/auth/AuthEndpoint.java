package club.myfpl.auth;

import club.myfpl.model.User;
import club.myfpl.resources.dto.CreateUserDTO;
import club.myfpl.services.UserService;
import club.myfpl.utils.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * User: Saket
 * Date: 10/01/17
 * Time: 2:47 PM
 */
@Component
@Path("/auth")
@Produces("application/json")
@Consumes("application/json")
public class AuthEndpoint {
    private final UserService userService;

    @Autowired
    public AuthEndpoint(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("create")
    public Response createUser(CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return Response.ok(createUserDTO).build();
    }

    @POST
    @Path("login")
    public Response authenticate(UserCredentials credentials, @Context HttpServletRequest request) {
        Optional<User> user = doAuthenticate(credentials);
        if (user.isPresent()) {
            User userToSend = user.get();
            request.getSession().invalidate();
            request.getSession(true).setAttribute(SessionConstants.USER, userToSend);
            return Response.ok(userToSend).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("logout")
    public Response logout(@Context HttpServletRequest request) {
        request.getSession().invalidate();
        return Response.ok().build();
    }

    private Optional<User> doAuthenticate(UserCredentials credentials) {
        User user = userService.authenticate(credentials.getEmail(), credentials.getPassword());
        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    private static class UserCredentials {
        private String email;
        private String password;

        String getEmail() {
            return email;
        }

        void setEmail(String email) {
            this.email = email;
        }

        String getPassword() {
            return password;
        }

        void setPassword(String password) {
            this.password = password;
        }
    }
}
