package club.myfpl.daos.impl;

import club.myfpl.daos.LeagueDAO;
import club.myfpl.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 4:06 PM
 */
@Service
public class LeagueDAOImpl implements LeagueDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public LeagueDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createLeague(League league) {
        mongoTemplate.insert(league);
    }

    @Override
    public void updateLeague(League league) {
        mongoTemplate.save(league);
    }

    @Override
    public League findLeague(long leagueId) {
        return mongoTemplate.findOne(Query.query(Criteria.where(League.LEAGUE_ID_KEY).is(leagueId)), League.class);
    }

    @Override
    public List<League> findLeaguesForAdmin(long adminUserId) {
        return mongoTemplate.find(Query.query(Criteria.where(League.ADMIN_USER_ID_KEY).is(adminUserId)), League.class);
    }

    @Override
    public List<League> findLeaguesForUser(long userId) {
        return mongoTemplate.find(Query.query(Criteria.where(League.USERS_KEY).in(userId)), League.class);
    }

    @Override
    public League findLeagueByInviteCode(String inviteCode) {
        return mongoTemplate.findOne(Query.query(Criteria.where(League.INVITE_CODE_KEY).is(inviteCode)), League.class);
    }
}
