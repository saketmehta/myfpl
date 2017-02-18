package club.myfpl.fpl;

import club.myfpl.daos.*;
import club.myfpl.fpl.responses.*;
import club.myfpl.model.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: Saket
 * Date: 11/01/17
 * Time: 9:51 PM
 */
@Service
public class FPLServiceImpl implements FPLService {

    private final FPLAPIClient    apiClient;
    private final StatusDAO       statusDAO;
    private final TeamDAO         teamDAO;
    private final ElementDAO      elementDAO;
    private final EventDAO        eventDAO;
    private final FixtureDAO      fixtureDAO;
    private final ElementStatsDAO elementStatsDAO;

    @Autowired
    public FPLServiceImpl(StatusDAO statusDAO, TeamDAO teamDAO, ElementDAO elementDAO, EventDAO eventDAO, FixtureDAO fixtureDAO, ElementStatsDAO elementStatsDAO) {
        this.statusDAO = statusDAO;
        this.teamDAO = teamDAO;
        this.elementDAO = elementDAO;
        this.eventDAO = eventDAO;
        this.fixtureDAO = fixtureDAO;
        this.elementStatsDAO = elementStatsDAO;
        this.apiClient = new FPLAPIClientImpl();
    }

    @Override
    public void grabAndSaveBootstrapDynamicData() {
        BootstrapDynamicDataResponse response = apiClient.fetchBootstrapDynamicData();

        Status status = new Status();
        status.setCurrentEvent(response.getCurrentEvent());
        status.setNextEvent(response.getNextEvent());
        status.setNextEventFixtures(response.getNext_event_fixtures().stream().map(FixtureResponse::toFixture).collect(Collectors.toList()));
        statusDAO.updateStatus(status);
    }

    @Override
    public void grabAndSaveBootstrapStaticData() {
        BootstrapStaticDataResponse response = apiClient.fetchBootstrapStaticData();

        List<ElementResponse> elementResponses = response.getElements();
        List<Element> elements = elementResponses.stream().map(ElementResponse::toElement).collect(Collectors.toList());
        elementDAO.updateElements(elements);

        List<TeamResponse> teamResponses = response.getTeams();
        List<Team> teams = teamResponses.stream().map(TeamResponse::toTeam).collect(Collectors.toList());
        teamDAO.updateTeams(teams);
    }

    @Override
    public void grabAndSaveEventData(int eventId) {
        EventDataResponse response = apiClient.fetchEventData(eventId);

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
}
