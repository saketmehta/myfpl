package club.myfpl.grabbers.responses;

import club.myfpl.beans.Element;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 5:36 PM
 */
public class ElementResponse {
    private long id;
    private String web_name;
    private String first_name;
    private String second_name;
    private long event_points;
    private long total_points;
    private long element_type;
    private long team;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeb_name() {
        return web_name;
    }

    public void setWeb_name(String web_name) {
        this.web_name = web_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public long getEvent_points() {
        return event_points;
    }

    public void setEvent_points(long event_points) {
        this.event_points = event_points;
    }

    public long getTotal_points() {
        return total_points;
    }

    public void setTotal_points(long total_points) {
        this.total_points = total_points;
    }

    public long getElement_type() {
        return element_type;
    }

    public void setElement_type(long element_type) {
        this.element_type = element_type;
    }

    public long getTeam() {
        return team;
    }

    public void setTeam(long team) {
        this.team = team;
    }

    public Element toElement() {
        Element element = new Element();
        element.setElementId(id);
        element.setWebName(web_name);
        element.setFirstName(first_name);
        element.setSecondName(second_name);
        element.setEventPoints(event_points);
        element.setTotalPoints(total_points);
        element.setElementType(element_type);
        element.setTeam(team);
        return element;
    }
}
