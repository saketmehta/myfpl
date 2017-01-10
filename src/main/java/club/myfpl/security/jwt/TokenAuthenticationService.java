package club.myfpl.security.jwt;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Saket
 * Date: 03/01/17
 * Time: 8:02 PM
 */
public interface TokenAuthenticationService {
    void addAuthentication(HttpServletResponse response, Authentication username);

    Authentication getAuthentication(HttpServletRequest request);
}
