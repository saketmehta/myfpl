package club.myfpl.grabbers.responses;

import club.myfpl.model.Fixture;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 5:35 PM
 */
public class FixtureResponse {
    private long id;
    private String kickoff_time_formatted;
    private String kickoff_time;
    private Long event;
    private Long team_a;
    private Long team_h;
    private long team_a_score;
    private long team_h_score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKickoff_time_formatted() {
        return kickoff_time_formatted;
    }

    public void setKickoff_time_formatted(String kickoff_time_formatted) {
        this.kickoff_time_formatted = kickoff_time_formatted;
    }

    public String getKickoff_time() {
        return kickoff_time;
    }

    public void setKickoff_time(String kickoff_time) {
        this.kickoff_time = kickoff_time;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Long getTeam_a() {
        return team_a;
    }

    public void setTeam_a(Long team_a) {
        this.team_a = team_a;
    }

    public Long getTeam_h() {
        return team_h;
    }

    public void setTeam_h(Long team_h) {
        this.team_h = team_h;
    }

    public long getTeam_a_score() {
        return team_a_score;
    }

    public void setTeam_a_score(long team_a_score) {
        this.team_a_score = team_a_score;
    }

    public long getTeam_h_score() {
        return team_h_score;
    }

    public void setTeam_h_score(long team_h_score) {
        this.team_h_score = team_h_score;
    }

    public Fixture toFixture() {
        Fixture fixture = new Fixture();
        fixture.setFixtureId(id);
        fixture.setKickoffTimeFormatted(kickoff_time_formatted);
        fixture.setKickoffTime(kickoff_time);
        fixture.setEvent(event);
        fixture.setTeamAway(team_a);
        fixture.setTeamHome(team_h);
        fixture.setTeamAwayScore(team_a_score);
        fixture.setTeamHomeScore(team_h_score);
        return fixture;
    }
}
