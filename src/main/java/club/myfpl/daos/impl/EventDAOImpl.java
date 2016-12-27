package club.myfpl.daos.impl;

import club.myfpl.beans.Event;
import club.myfpl.daos.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 6:10 PM
 */
@Service
public class EventDAOImpl implements EventDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public EventDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateEvent(Event event) {
        Update update = new Update();
        update.set(Event.FIXTURES_KEY, event.getFixtures());
        mongoTemplate.upsert(Query.query(Criteria.where(Event.EVENT_ID_KEY).is(event.getEventId())), update, Event.class);
    }
}
