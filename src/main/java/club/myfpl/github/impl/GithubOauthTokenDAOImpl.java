package club.myfpl.github.impl;

import club.myfpl.github.GithubOauthToken;
import club.myfpl.github.GithubOauthTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 11:39 PM
 */
@Service
public class GithubOauthTokenDAOImpl implements GithubOauthTokenDAO {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public GithubOauthTokenDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void saveGithubOauthToken(GithubOauthToken token) {
        mongoTemplate.save(token);
    }

    @Override
    public GithubOauthToken findGithubOauthToken(String token) {
        return mongoTemplate.findOne(Query.query(Criteria.where(GithubOauthToken.ACCESS_TOKEN_KEY).is(token)), GithubOauthToken.class);
    }
}
