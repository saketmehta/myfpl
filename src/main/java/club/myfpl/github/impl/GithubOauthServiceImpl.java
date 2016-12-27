package club.myfpl.github.impl;

import club.myfpl.github.GithubOauthService;
import club.myfpl.github.GithubOauthToken;
import club.myfpl.github.GithubOauthTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 11:34 PM
 */
@Service
public class GithubOauthServiceImpl implements GithubOauthService {
    private final GithubOauthTokenDAO githubOauthTokenDAO;

    @Autowired
    public GithubOauthServiceImpl(GithubOauthTokenDAO githubOauthTokenDAO) {
        this.githubOauthTokenDAO = githubOauthTokenDAO;
    }

    @Override
    public void saveToken(GithubOauthToken token) {
        githubOauthTokenDAO.saveGithubOauthToken(token);
    }

    @Override
    public long findUserWithToken(String token) {
        GithubOauthToken githubOauthToken = githubOauthTokenDAO.findGithubOauthToken(token);
        return githubOauthToken.getUserId();
    }
}
