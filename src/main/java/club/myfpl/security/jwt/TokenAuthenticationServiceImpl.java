package club.myfpl.security.jwt;

import club.myfpl.model.User;
import club.myfpl.services.UserService;
import club.myfpl.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    private final UserService userService;

    @Autowired
    public TokenAuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addAuthentication(HttpServletResponse response, String email) {
        User user = userService.fetchUserByEmail(email);
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("user", JsonUtils.toJson(user));
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
        User user = JsonUtils.fromJson((String) claims.get("user"), User.class);
        if (user != null && user.getEmail() != null) {
            List<GrantedAuthority> authorities;
            if (CollectionUtils.isEmpty(user.getRoles())) {
                authorities = AuthorityUtils.createAuthorityList("USER");
            } else {
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.rolesAsCommaSeparatedString());
            }
            return new JwtAuthentication(authorities, token, user);
        }
        return null;
    }
}
