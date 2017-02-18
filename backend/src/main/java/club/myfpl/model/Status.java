package club.myfpl.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 12:37 AM
 */
@Document
public class Status {
    private String        id;
    private long          currentEvent;
    private long          nextEvent;
    private List<Fixture> nextEventFixtures;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(long currentEvent) {
        this.currentEvent = currentEvent;
    }

    public long getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(long nextEvent) {
        this.nextEvent = nextEvent;
    }

    public List<Fixture> getNextEventFixtures() {
        return nextEventFixtures;
    }

    public void setNextEventFixtures(List<Fixture> nextEventFixtures) {
        this.nextEventFixtures = nextEventFixtures;
    }
}
