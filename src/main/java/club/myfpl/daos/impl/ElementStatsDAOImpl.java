package club.myfpl.daos.impl;

import club.myfpl.beans.ElementStats;
import club.myfpl.daos.ElementStatsDAO;
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
 * Time: 6:21 PM
 */
@Service
public class ElementStatsDAOImpl implements ElementStatsDAO {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ElementStatsDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateElementStats(List<ElementStats> elementStatsList) {
        for (ElementStats stats : elementStatsList) {
            Update update = new Update();
            update.set(ElementStats.TOTAL_POINTS_KEY, stats.getTotalPoints());
            Criteria criteria = Criteria.where(ElementStats.ELEMENT_ID_KEY).is(stats.getElementId());
            criteria.and(ElementStats.EVENT_ID_KEY).is(stats.getEventId());
            mongoTemplate.upsert(Query.query(criteria), update, ElementStats.class);
        }
    }
}
