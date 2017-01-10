package club.myfpl.security.jwt;

import club.myfpl.security.model.AuthenticatedUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * User: Saket
 * Date: 04/01/17
 * Time: 12:48 PM
 */
public class JwtAuthentication extends AbstractAuthenticationToken {
    private String token;
    private AuthenticatedUser authenticatedUser;

    JwtAuthentication(String token, AuthenticatedUser authenticatedUser) {
        super(authenticatedUser.getAuthorities());
        this.token = token;
        this.authenticatedUser = authenticatedUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return authenticatedUser;
    }
}
