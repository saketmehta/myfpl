package club.myfpl.services.impl;

import club.myfpl.model.League;
import club.myfpl.model.SequenceNumber;
import club.myfpl.model.User;
import club.myfpl.services.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 28/11/16
 * Time: 5:16 PM
 */
@Service
public class SequenceNumberServiceImpl implements SequenceNumberService {
    private static final long DEFAULT_START_SEQUENCE = 1000;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public SequenceNumberServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long nextSequenceNumber(Class clazz) {
        SequenceNumber sequenceNumber = mongoTemplate.findOne(new Query(), SequenceNumber.class);
        if (sequenceNumber == null) {
            sequenceNumber = new SequenceNumber();
        }
        long number = DEFAULT_START_SEQUENCE;
        if (User.class == clazz) {
            if (sequenceNumber.getUserSeqNumber() >= DEFAULT_START_SEQUENCE) {
                number = sequenceNumber.getUserSeqNumber();
            }
            sequenceNumber.setUserSeqNumber(number + 1);
        } else if (League.class == clazz) {
            if (sequenceNumber.getLeagueSeqNumber() >= DEFAULT_START_SEQUENCE) {
                number = sequenceNumber.getLeagueSeqNumber();
            }
            sequenceNumber.setLeagueSeqNumber(number + 1);
        }
        mongoTemplate.save(sequenceNumber);
        return number;
    }
}
