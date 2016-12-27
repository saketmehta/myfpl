package club.myfpl.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:49 AM
 */
@JsonIgnoreProperties
public class Event {
    public static final String EVENT_ID_KEY = "eventId";
    public static final String FIXTURES_KEY = "fixtures";

    private String id;
    @Indexed(unique = true)
    private long eventId;
    private List<Long> fixtures;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public List<Long> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Long> fixtures) {
        this.fixtures = fixtures;
    }
}
