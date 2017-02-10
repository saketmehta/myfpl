package club.myfpl.resources.dto;

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
    private Long   leagueId;
    @NotBlank
    private String name;
    @Range(min = 1)
    private Long   adminUserId;
    @Range(min = 2, max = 5)
    private Long   capacity;
    @NotBlank
    private String inviteCode;

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
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