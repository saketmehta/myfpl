package club.myfpl.security.services;

import club.myfpl.model.User;
import club.myfpl.security.model.AuthenticatedUser;
import club.myfpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 27/12/16
 * Time: 4:18 PM
 */
@Service
public class AuthenticatedUserService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public AuthenticatedUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.fetchUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found!");
        } else {
            return new AuthenticatedUser(user);
        }
    }
}
