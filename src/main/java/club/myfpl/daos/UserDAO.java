package club.myfpl.daos;

import club.myfpl.beans.User;

/**
 * @author Saket on 15/09/16.
 */
public interface UserDAO {
    void createUser(User user);

    void updateUser(User user);

    User findUser(long userId);

    User findUserByEmail(String email);
}
