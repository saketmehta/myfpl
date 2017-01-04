package club.myfpl.model;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 5:10 PM
 */
public class ElementStats {
    public static final String ELEMENT_ID_KEY = "elementId";
    public static final String EVENT_ID_KEY = "eventId";
    public static final String TOTAL_POINTS_KEY = "totalPoints";

    private String id;
    private long elementId;
    private long eventId;
    private long totalPoints;

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

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(long totalPoints) {
        this.totalPoints = totalPoints;
    }
}
