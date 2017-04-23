package club.myfpl.fpl.responses;

import club.myfpl.domain.Club;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 5:37 PM
 */
public class TeamResponse {

    private long   id;
    private String name;
    private String short_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public Club toClub() {
        Club club = new Club();
        club.setId(id);
        club.setName(name);
        club.setShortName(short_name);
        return club;
    }

}
