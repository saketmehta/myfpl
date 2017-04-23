package club.myfpl.resources.dto;

import club.myfpl.domain.League;
import com.google.common.collect.Sets;

/**
 * User: Saket
 * Date: 19/02/17
 * Time: 4:38 PM
 */
public class CreateLeagueDTO {

    private String name;
    private Long   capacity;
    private String inviteCode;
    private String adminUserId;

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

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public League toLeague() {
        League league = new League();
        league.setName(name);
        league.setCapacity(capacity);
        league.setInviteCode(inviteCode);
        league.setAdminUserId(adminUserId);
        league.setUsers(Sets.newHashSet(adminUserId));
        return league;
    }

}
