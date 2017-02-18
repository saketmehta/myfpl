package club.myfpl.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: Saket
 * Date: 24/11/16
 * Time: 11:45 PM
 */
@Document
public class Team {
    public static final String TEAM_ID_KEY    = "teamId";
    public static final String NAME_KEY       = "name";
    public static final String SHORT_NAME_KEY = "shortName";

    private String id;
    @Indexed(unique = true)
    private long   teamId;
    private String name;
    private String shortName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
