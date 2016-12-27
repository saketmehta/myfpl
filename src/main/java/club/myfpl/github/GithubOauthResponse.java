package club.myfpl.github;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 11:29 PM
 */
public class GithubOauthResponse {
    private String access_token;
    private String scope;
    private String token_type;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String token_type) {
        this.token_type = token_type;
    }
}
