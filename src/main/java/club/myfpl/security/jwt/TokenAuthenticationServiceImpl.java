package club.myfpl.security.jwt;

import club.myfpl.security.model.AuthenticatedUser;
import club.myfpl.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Saket
 * Date: 03/01/17
 * Time: 8:03 PM
 */
@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {
    private static final String SECRET = "WelcomeToMyHouse!";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_KEY = "Authorization";

    @Override
    public void addAuthentication(HttpServletResponse response, Authentication authentication) {
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(authenticatedUser.getEmail());
        claims.put("user", JsonUtils.toJson(authenticatedUser));
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
        response.addHeader(HEADER_KEY, TOKEN_PREFIX + " " + token);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_KEY);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        token = token.substring(TOKEN_PREFIX.length());
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        AuthenticatedUser authenticatedUser = JsonUtils.fromJson((String) claims.get("user"), AuthenticatedUser.class);
        if (authenticatedUser != null) {
            return new JwtAuthentication(token, authenticatedUser);
        }
        return null;
    }
}
