package club.myfpl.daos.impl;

import club.myfpl.daos.ElementDAO;
import club.myfpl.model.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Saket on 15/09/16.
 */
@Service
public class ElementDAOImpl implements ElementDAO {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ElementDAOImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateElements(List<Element> elements) {
        for (Element element : elements) {
            Update update = new Update();
            update.set(Element.WEB_NAME_KEY, element.getWebName());
            update.set(Element.FIRST_NAME_KEY, element.getFirstName());
            update.set(Element.SECOND_NAME_KEY, element.getSecondName());
            update.set(Element.EVENT_POINTS_KEY, element.getEventPoints());
            update.set(Element.TOTAL_POINTS_KEY, element.getTotalPoints());
            update.set(Element.ELEMENT_TYPE_KEY, element.getElementType());
            update.set(Element.TEAM_KEY, element.getTeam());
            mongoTemplate.upsert(Query.query(Criteria.where(Element.ELEMENT_ID_KEY).is(element.getElementId())), update, Element.class);
        }
    }
}
