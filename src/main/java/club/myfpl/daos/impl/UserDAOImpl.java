package club.myfpl.daos.impl;

import club.myfpl.beans.User;
import club.myfpl.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author Saket on 15/09/16.
 */
@Service
public class UserDAOImpl implements UserDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createUser(User user) {
        mongoTemplate.insert(user);
    }

    @Override
    public void updateUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUser(long userId) {
        return mongoTemplate.findOne(Query.query(Criteria.where(User.USER_ID_KEY).is(userId)), User.class);
    }

    @Override
    public User findUserByEmail(String email) {
        return mongoTemplate.findOne(Query.query(Criteria.where(User.EMAIL_KEY).is(email)), User.class);
    }
}
