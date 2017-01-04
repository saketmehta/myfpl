package club.myfpl.grabbers;

import club.myfpl.daos.ElementStatsDAO;
import club.myfpl.daos.EventDAO;
import club.myfpl.daos.FixtureDAO;
import club.myfpl.daos.StatusDAO;
import club.myfpl.grabbers.responses.EventDataResponse;
import club.myfpl.grabbers.responses.FixtureResponse;
import club.myfpl.model.ElementStats;
import club.myfpl.model.Event;
import club.myfpl.model.Fixture;
import club.myfpl.utils.JsonUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:30 AM
 */
@Component
public class EventGrabber extends AbstractGrabber {

    private final StatusDAO statusDAO;
    private final EventDAO eventDAO;
    private final FixtureDAO fixtureDAO;
    private final ElementStatsDAO elementStatsDAO;

    @Autowired
    public EventGrabber(StatusDAO statusDAO, EventDAO eventDAO, FixtureDAO fixtureDAO, ElementStatsDAO elementStatsDAO) {
        this.statusDAO = statusDAO;
        this.eventDAO = eventDAO;
        this.fixtureDAO = fixtureDAO;
        this.elementStatsDAO = elementStatsDAO;
    }

    @Override
    public void grabAndSaveData() {
        Long currentEvent = statusDAO.fetchCurrentEvent();
        doGrabAndSave(currentEvent);
    }

    private void saveGrabbedData(EventDataResponse response) {
        Event event = new Event();
        event.setEventId(response.getFixtures().get(0).getEvent()); // take the event from any fixture
        event.setFixtures(response.getFixtures().stream().map(FixtureResponse::getId).collect(Collectors.toList()));
        eventDAO.updateEvent(event);

        List<ElementStats> elementStatsList = Lists.newArrayList();
        for (Map.Entry<Long, EventDataResponse.ElementDetails> elementDetailsEntry : response.getElements().entrySet()) {
            ElementStats stats = new ElementStats();
            stats.setElementId(elementDetailsEntry.getKey());
            stats.setEventId(event.getEventId());
            stats.setTotalPoints(elementDetailsEntry.getValue().getStats().getTotal_points());
            elementStatsList.add(stats);
        }
        elementStatsDAO.updateElementStats(elementStatsList);

        List<Fixture> fixtures = response.getFixtures().stream().map(FixtureResponse::toFixture).collect(Collectors.toList());
        fixtureDAO.updateFixtures(fixtures);
    }

    public void grabAll() {
        Long currentEvent = statusDAO.fetchCurrentEvent();
        for (long i = 1; i <= currentEvent; i++) {
            doGrabAndSave(i);
        }
    }

    public void grabEvent(long eventNumber) {
        doGrabAndSave(eventNumber);
    }

    private void doGrabAndSave(long eventNumber) {
        String json = readUrl(getEndpointForEvent(eventNumber));
        EventDataResponse event = JsonUtils.fromJson(json, EventDataResponse.class);
        saveGrabbedData(event);
    }

    private String getEndpointForEvent(long event) {
        return BASE_FPL_ENDPOINT + "/event/" + event + "/live";
    }
}
