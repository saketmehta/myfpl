package club.myfpl.auth;

import club.myfpl.services.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

/**
 * User: Saket
 * Date: 12/01/17
 * Time: 8:04 PM
 */
@Component
@SpringBootTest
public class AuthEndpointTest {

    @Autowired
    private UserService userService;

    @Test
    public void authenticate() throws Exception {
        System.out.println("Hello!");
    }
}