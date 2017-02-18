package club.myfpl.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: Saket
 * Date: 24/11/16
 * Time: 11:59 PM
 */
@Document
public class Fixture {
    public static final String FIXTURE_ID_KEY             = "fixtureId";
    public static final String KICKOFF_TIME_FORMATTED_KEY = "kickoffTimeFormatted";
    public static final String KICKOFF_TIME_KEY           = "kickoffTime";
    public static final String EVENT_KEY                  = "event";
    public static final String TEAM_AWAY_KEY              = "teamAway";
    public static final String TEAM_HOME_KEY              = "teamHome";
    public static final String TEAM_AWAY_SCORE_KEY        = "teamAwayScore";
    public static final String TEAM_HOME_SCORE_KEY        = "teamHomeScore";

    private String id;
    @Indexed(unique = true)
    private long   fixtureId;
    private String kickoffTimeFormatted;
    private String kickoffTime;
    private long   event;
    private long   teamAway;
    private long   teamHome;
    private long   teamAwayScore;
    private long   teamHomeScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public String getKickoffTimeFormatted() {
        return kickoffTimeFormatted;
    }

    public void setKickoffTimeFormatted(String kickoffTimeFormatted) {
        this.kickoffTimeFormatted = kickoffTimeFormatted;
    }

    public String getKickoffTime() {
        return kickoffTime;
    }

    public void setKickoffTime(String kickoffTime) {
        this.kickoffTime = kickoffTime;
    }

    public long getEvent() {
        return event;
    }

    public void setEvent(long event) {
        this.event = event;
    }

    public long getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(long teamAway) {
        this.teamAway = teamAway;
    }

    public long getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(long teamHome) {
        this.teamHome = teamHome;
    }

    public long getTeamAwayScore() {
        return teamAwayScore;
    }

    public void setTeamAwayScore(long teamAwayScore) {
        this.teamAwayScore = teamAwayScore;
    }

    public long getTeamHomeScore() {
        return teamHomeScore;
    }

    public void setTeamHomeScore(long teamHomeScore) {
        this.teamHomeScore = teamHomeScore;
    }
}
