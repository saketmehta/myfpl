package club.myfpl.github;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 11:47 PM
 */
public interface GithubOauthTokenDAO {
    void saveGithubOauthToken(GithubOauthToken token);

    GithubOauthToken findGithubOauthToken(String token);
}
