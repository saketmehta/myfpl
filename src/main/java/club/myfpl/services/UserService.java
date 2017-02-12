package club.myfpl.services;

import club.myfpl.model.User;
import club.myfpl.resources.dto.UpdateUserDTO;

/**
 * User: Saket
 * Date: 28/11/16
 * Time: 5:03 PM
 */
public interface UserService {
    User createUser(User user);

    User updateUser(UpdateUserDTO updateUserDTO);

    User fetchUser(long userId);

    User fetchUserByEmail(String email);

    User authenticate(String email, String password);

    boolean updatePassword(long userId, String oldPassword, String newPassword);
}
