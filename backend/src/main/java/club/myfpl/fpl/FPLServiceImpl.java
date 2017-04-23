package club.myfpl.fpl;

import club.myfpl.domain.Club;
import club.myfpl.domain.Player;
import club.myfpl.fpl.responses.*;
import club.myfpl.model.ElementStats;
import club.myfpl.model.Event;
import club.myfpl.model.Fixture;
import club.myfpl.model.Status;
import club.myfpl.repositories.ElementStatsDAO;
import club.myfpl.repositories.EventDAO;
import club.myfpl.repositories.FixtureDAO;
import club.myfpl.repositories.StatusDAO;
import club.myfpl.services.ClubService;
import club.myfpl.services.PlayerService;
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
    private final ClubService     clubService;
    private final PlayerService   playerService;
    private final EventDAO        eventDAO;
    private final FixtureDAO      fixtureDAO;
    private final ElementStatsDAO elementStatsDAO;

    @Autowired
    public FPLServiceImpl(StatusDAO statusDAO, ClubService clubService, PlayerService playerService, EventDAO eventDAO, FixtureDAO fixtureDAO, ElementStatsDAO elementStatsDAO) {
        this.statusDAO = statusDAO;
        this.clubService = clubService;
        this.playerService = playerService;
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
        List<Player> players = elementResponses.stream().map(ElementResponse::toPlayer).collect(Collectors.toList());
        playerService.getPlayerRepository().save(players);

        List<TeamResponse> teamResponses = response.getTeams();
        List<Club> clubs = teamResponses.stream().map(TeamResponse::toClub).collect(Collectors.toList());
        clubService.getClubRepository().save(clubs);
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
