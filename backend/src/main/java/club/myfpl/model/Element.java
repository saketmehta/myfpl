package club.myfpl.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: Saket
 * Date: 15/09/16
 * Time: 3:49 AM
 */
@Document
public class Element {
    public static final String ELEMENT_ID_KEY   = "elementId";
    public static final String WEB_NAME_KEY     = "webName";
    public static final String FIRST_NAME_KEY   = "firstName";
    public static final String SECOND_NAME_KEY  = "secondName";
    public static final String EVENT_POINTS_KEY = "eventPoints";
    public static final String TOTAL_POINTS_KEY = "totalPoints";
    public static final String ELEMENT_TYPE_KEY = "elementType";
    public static final String TEAM_KEY         = "team";

    private String id;
    @Indexed(unique = true)
    private long   elementId;
    private String webName;
    private String firstName;
    private String secondName;
    private Long   eventPoints;
    private Long   totalPoints;
    private Long   elementType;
    private Long   team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getElementId() {
        return elementId;
    }

    public void setElementId(long elementId) {
        this.elementId = elementId;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Long getEventPoints() {
        return eventPoints;
    }

    public void setEventPoints(Long eventPoints) {
        this.eventPoints = eventPoints;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Long getElementType() {
        return elementType;
    }

    public void setElementType(Long elementType) {
        this.elementType = elementType;
    }

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }
}
