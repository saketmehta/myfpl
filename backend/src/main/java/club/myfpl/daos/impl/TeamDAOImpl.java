package club.myfpl.daos.impl;

import club.myfpl.daos.TeamDAO;
import club.myfpl.model.Team;
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
 * Time: 5:54 PM
 */
@Service
public class TeamDAOImpl implements TeamDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TeamDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateTeams(List<Team> teams) {
        for (Team team : teams) {
            Update update = new Update();
            update.set(Team.NAME_KEY, team.getName());
            update.set(Team.SHORT_NAME_KEY, team.getShortName());
            mongoTemplate.upsert(Query.query(Criteria.where(Team.TEAM_ID_KEY).is(team.getTeamId())), update, Team.class);
        }
    }

    @Override
    public List<Team> fetchTeams() {
        return mongoTemplate.findAll(Team.class);
    }
}
