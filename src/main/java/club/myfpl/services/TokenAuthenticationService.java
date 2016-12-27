package club.myfpl.services;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * User: Saket
 * Date: 26/12/16
 * Time: 1:33 PM
 */
public interface TokenAuthenticationService {
    void addAuthentication(HttpServletResponse response, String username) throws UnsupportedEncodingException;

    Authentication getAuthentication(HttpServletRequest request) throws UnsupportedEncodingException;
}
