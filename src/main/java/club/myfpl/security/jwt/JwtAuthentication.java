package club.myfpl.security.jwt;

import club.myfpl.model.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * User: Saket
 * Date: 04/01/17
 * Time: 12:48 PM
 */
public class JwtAuthentication extends AbstractAuthenticationToken {
    private String token;
    private User user;

    JwtAuthentication(Collection<? extends GrantedAuthority> authorities, String token, User user) {
        super(authorities);
        this.token = token;
        this.user = user;
        setAuthenticated(true);
        setDetails(user);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return user.getEmail();
    }
}
