package club.myfpl.grabbers;

import club.myfpl.daos.StatusDAO;
import club.myfpl.grabbers.responses.BootstrapDynamicDataResponse;
import club.myfpl.grabbers.responses.FixtureResponse;
import club.myfpl.model.Status;
import club.myfpl.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * User: Saket
 * Date: 24/11/16
 * Time: 11:39 PM
 */
@Service
public class BootstrapDynamicDataGrabber extends AbstractGrabber {

    private static final String BOOTSTRAP_DYNAMIC_ENDPOINT = "/bootstrap-dynamic";
    private final StatusDAO statusDAO;

    @Autowired
    public BootstrapDynamicDataGrabber(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    @Override
    public void grabAndSaveData() {
        String json = readUrl(BASE_FPL_ENDPOINT + BOOTSTRAP_DYNAMIC_ENDPOINT);
        BootstrapDynamicDataResponse response = JsonUtils.fromJson(json, BootstrapDynamicDataResponse.class);
        saveGrabbedData(response);
    }

    private void saveGrabbedData(BootstrapDynamicDataResponse response) {
        Status status = new Status();
        status.setCurrentEvent(response.getCurrentEvent());
        status.setNextEvent(response.getNextEvent());
        status.setNextEventFixtures(response.getNext_event_fixtures().stream().map(FixtureResponse::toFixture).collect(Collectors.toList()));
        statusDAO.updateStatus(status);
    }
}
