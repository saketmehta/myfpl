package club.myfpl.daos.impl;

import club.myfpl.beans.Status;
import club.myfpl.daos.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:36 AM
 */
@Service
public class StatusDAOImpl implements StatusDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public StatusDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long fetchCurrentEvent() {
        Status status = mongoTemplate.findOne(new Query(), Status.class);
        if (status == null) {
            throw new RuntimeException("No status found!");
        }
        return status.getCurrentEvent();
    }

    @Override
    public void updateStatus(Status status) {
        mongoTemplate.dropCollection(Status.class);
        mongoTemplate.insert(status);
    }
}
