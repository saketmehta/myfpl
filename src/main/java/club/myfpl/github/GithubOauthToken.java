package club.myfpl.github;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 11:35 PM
 */
@Document
public class GithubOauthToken extends UsernamePasswordAuthenticationToken {
    public static final String ACCESS_TOKEN_KEY = "accessToken";
    public static final String USER_ID_KEY = "userId";

    private String id;
    private String accessToken;
    private long userId;

    public GithubOauthToken(long userId, String accessToken) {
        super(userId, accessToken);
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public GithubOauthToken(long userId, String accessToken, Collection<? extends GrantedAuthority> authorities) {
        super(userId, accessToken, authorities);
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
