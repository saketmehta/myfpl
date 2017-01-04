package club.myfpl.grabbers;

import club.myfpl.daos.ElementDAO;
import club.myfpl.daos.TeamDAO;
import club.myfpl.grabbers.responses.BootstrapStaticDataResponse;
import club.myfpl.grabbers.responses.ElementResponse;
import club.myfpl.grabbers.responses.TeamResponse;
import club.myfpl.model.Element;
import club.myfpl.model.Team;
import club.myfpl.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Saket
 * Date: 24/11/16
 * Time: 11:39 PM
 */
@Service
public class BootstrapStaticDataGrabber extends AbstractGrabber {

    private static final String BOOTSTRAP_STATIC_ENDPOINT = "/bootstrap-static";
    private final TeamDAO teamDAO;
    private final ElementDAO elementDAO;

    @Autowired
    public BootstrapStaticDataGrabber(TeamDAO teamDAO, ElementDAO elementDAO) {
        this.teamDAO = teamDAO;
        this.elementDAO = elementDAO;
    }

    @Override
    public void grabAndSaveData() {
        String json = readUrl(BASE_FPL_ENDPOINT + BOOTSTRAP_STATIC_ENDPOINT);
        BootstrapStaticDataResponse bootstrapStaticDataResponse = JsonUtils.fromJson(json, BootstrapStaticDataResponse.class);
        saveGrabbedData(bootstrapStaticDataResponse);
    }

    private void saveGrabbedData(BootstrapStaticDataResponse response) {
        List<ElementResponse> elementResponses = response.getElements();
        List<TeamResponse> teamResponses = response.getTeams();

        List<Element> elements = elementResponses.stream().map(ElementResponse::toElement).collect(Collectors.toList());
        List<Team> teams = teamResponses.stream().map(TeamResponse::toTeam).collect(Collectors.toList());

        elementDAO.updateElements(elements);
        teamDAO.updateTeams(teams);
    }
}
