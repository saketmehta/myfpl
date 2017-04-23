package club.myfpl.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: Saket
 * Date: 15/09/16
 * Time: 3:49 AM
 */
@Document
public class Player {

    private Long   id;
    private String webName;
    private String firstName;
    private String secondName;
    private Long   eventPoints;
    private Long   totalPoints;
    private Long   elementType;
    private Long   clubId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

}
