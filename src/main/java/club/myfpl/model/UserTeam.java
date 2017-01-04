package club.myfpl.model;

import club.myfpl.utils.Tuple;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * @author Saket on 15/09/16.
 */
@Document
public class UserTeam {
    private String id;
    private Long teamId;
    private Long leagueId;
    private Long userId;
    private Set<Long> elements;
    private Long event;
    private List<Tuple<Long, Long>> transfers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Long> getElements() {
        return elements;
    }

    public void setElements(Set<Long> elements) {
        this.elements = elements;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public List<Tuple<Long, Long>> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Tuple<Long, Long>> transfers) {
        this.transfers = transfers;
    }
}
