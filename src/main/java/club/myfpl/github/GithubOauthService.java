package club.myfpl.github;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 11:47 PM
 */
public interface GithubOauthService {
    void saveToken(GithubOauthToken token);

    long findUserWithToken(String token);
}
