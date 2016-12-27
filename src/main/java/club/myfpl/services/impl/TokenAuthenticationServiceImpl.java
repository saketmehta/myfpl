package club.myfpl.services.impl;

import club.myfpl.security.AuthenticatedUser;
import club.myfpl.security.AuthenticatedUserService;
import club.myfpl.services.TokenAuthenticationService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * User: Saket
 * Date: 26/12/16
 * Time: 1:38 AM
 */
@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    private static final String ISSUER = "MyFPL";
    private static final String SECRET = "ThisIsASecret";
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public TokenAuthenticationServiceImpl(AuthenticatedUserService authenticatedUserService) {
        this.authenticatedUserService = authenticatedUserService;
    }

    @Override
    public void addAuthentication(HttpServletResponse response, String username) throws UnsupportedEncodingException {
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET));
        response.addHeader(AUTH_HEADER_NAME, token);
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) throws UnsupportedEncodingException {
        String token = request.getHeader(AUTH_HEADER_NAME);
        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(null, null);
        authResult.setAuthenticated(false);
        if (token != null) {
            // parse the token.
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(SECRET)).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String username = decodedJWT.getSubject();
            if (username != null) { // we managed to retrieve a user
                AuthenticatedUser authenticatedUser = (AuthenticatedUser) authenticatedUserService.loadUserByUsername(username);
                authResult = new UsernamePasswordAuthenticationToken(authenticatedUser.getUsername(), authenticatedUser.getPassword(), authenticatedUser.getAuthorities());
                authResult.setDetails(authenticatedUser.getUser());
            }
        }
        return authResult;
    }
}
