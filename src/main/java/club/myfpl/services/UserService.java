package club.myfpl.services;

import club.myfpl.model.User;

/**
 * User: Saket
 * Date: 28/11/16
 * Time: 5:03 PM
 */
public interface UserService {
    User createUser(User user);

    User updateUser(long userId, User user);

    User fetchUser(long userId);

    User fetchUserByEmail(String email);

    User authenticate(String email, String password);
}
