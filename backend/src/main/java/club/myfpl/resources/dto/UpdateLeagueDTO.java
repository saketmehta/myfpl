package club.myfpl.resources.dto;

/**
 * @author Saket
 * @since 23/04/17.
 */
public class UpdateLeagueDTO {

    private String id;
    private String name;
    private Long   capacity;
    private String inviteCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

}
