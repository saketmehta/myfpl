package club.myfpl.resources;

import club.myfpl.model.User;
import club.myfpl.resources.dto.UpdateUserDTO;
import club.myfpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * User: Saket
 * Date: 08/02/17
 * Time: 5:50 PM
 */
@Component
@Path("user")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("update")
    public Response updateUser(UpdateUserDTO updateUserDTO) {
        User user = userService.updateUser(updateUserDTO);
        return Response.ok(user).build();
    }

    @POST
    @Path("change-password")
    public Response changePassword(UserCredentialsDTO userCredentialsDTO) {
        boolean updated = userService.updatePassword(userCredentialsDTO.userId, userCredentialsDTO.oldPassword, userCredentialsDTO.newPassword);
        if (updated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(false).build();
        }
    }

    @GET
    @Path("fetch/{userId}")
    public Response fetchUser(@PathParam("userId") long userId) {
        return Response.ok(userService.fetchUser(userId)).build();
    }

    private static class UserCredentialsDTO {
        private Long   userId;
        private String oldPassword;
        private String newPassword;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}
