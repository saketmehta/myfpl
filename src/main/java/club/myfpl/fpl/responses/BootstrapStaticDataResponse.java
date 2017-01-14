package club.myfpl.fpl.responses;

import java.util.List;

/**
 * User: Saket
 * Date: 24/11/16
 * Time: 11:42 PM
 */
public class BootstrapStaticDataResponse {
    private List<ElementResponse> elements;
    private List<TeamResponse>    teams;

    public List<ElementResponse> getElements() {
        return elements;
    }

    public void setElements(List<ElementResponse> elements) {
        this.elements = elements;
    }

    public List<TeamResponse> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamResponse> teams) {
        this.teams = teams;
    }

}
