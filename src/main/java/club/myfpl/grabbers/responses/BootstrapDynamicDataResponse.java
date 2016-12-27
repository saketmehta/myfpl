package club.myfpl.grabbers.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 6:42 PM
 */
public class BootstrapDynamicDataResponse {
    @SerializedName("current-event")
    private long currentEvent;
    @SerializedName("next-event")
    private long nextEvent;
    private List<FixtureResponse> next_event_fixtures;

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

    public List<FixtureResponse> getNext_event_fixtures() {
        return next_event_fixtures;
    }

    public void setNext_event_fixtures(List<FixtureResponse> next_event_fixtures) {
        this.next_event_fixtures = next_event_fixtures;
    }
}
