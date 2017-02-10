package club.myfpl.daos.impl;

import club.myfpl.beans.Fixture;
import club.myfpl.daos.FixtureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 6:15 PM
 */
@Service
public class FixtureDAOImpl implements FixtureDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public FixtureDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateFixtures(List<Fixture> fixtures) {
        for (Fixture fixture : fixtures) {
            Update update = new Update();
            update.set(Fixture.KICKOFF_TIME_FORMATTED_KEY, fixture.getKickoffTimeFormatted());
            update.set(Fixture.KICKOFF_TIME_KEY, fixture.getKickoffTime());
            update.set(Fixture.EVENT_KEY, fixture.getEvent());
            update.set(Fixture.TEAM_AWAY_KEY, fixture.getTeamAway());
            update.set(Fixture.TEAM_HOME_KEY, fixture.getTeamHome());
            update.set(Fixture.TEAM_AWAY_SCORE_KEY, fixture.getTeamAwayScore());
            update.set(Fixture.TEAM_HOME_SCORE_KEY, fixture.getTeamHomeScore());
            mongoTemplate.upsert(Query.query(Criteria.where(Fixture.FIXTURE_ID_KEY).is(fixture.getFixtureId())), update, Fixture.class);
        }
    }
}
