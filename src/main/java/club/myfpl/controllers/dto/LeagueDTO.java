package club.myfpl.controllers.dto;

import club.myfpl.model.League;
import com.google.common.collect.Sets;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 3:16 PM
 */
public class LeagueDTO {
    private long leagueId;
    @NotBlank
    private String name;
    @Range(min = 1)
    private long adminUserId;
    @Range(min = 2, max = 5)
    private long capacity;
    @NotBlank
    private String inviteCode;

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public League toLeague() {
        League league = new League();
        league.setName(name);
        league.setAdminUserId(adminUserId);
        league.setCapacity(capacity);
        league.setInviteCode(inviteCode);
        league.setUsers(Sets.newHashSet(adminUserId));
        return league;
    }
}