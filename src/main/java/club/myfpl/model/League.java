package club.myfpl.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

/**
 * @author Saket on 15/09/16.
 */
@Document
public class League {
    public static final String LEAGUE_ID_KEY     = "leagueId";
    public static final String ADMIN_USER_ID_KEY = "adminUserId";
    public static final String USERS_KEY         = "users";
    public static final String INVITE_CODE_KEY   = "inviteCode";

    private String          id;
    @Indexed(unique = true)
    private Long            leagueId;
    private String          name;
    private Long            adminUserId;
    private Long            capacity;
    private Set<Long>       users;
    private Boolean         isLocked;
    private Long            transferTurnUserId;
    private Long            firstTransferUserId;
    private Map<Long, Long> transferOrder;
    @Indexed(unique = true)
    private String          inviteCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Set<Long> getUsers() {
        return users;
    }

    public void setUsers(Set<Long> users) {
        this.users = users;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Long getTransferTurnUserId() {
        return transferTurnUserId;
    }

    public void setTransferTurnUserId(Long transferTurnUserId) {
        this.transferTurnUserId = transferTurnUserId;
    }

    public Long getFirstTransferUserId() {
        return firstTransferUserId;
    }

    public void setFirstTransferUserId(Long firstTransferUserId) {
        this.firstTransferUserId = firstTransferUserId;
    }

    public Map<Long, Long> getTransferOrder() {
        return transferOrder;
    }

    public void setTransferOrder(Map<Long, Long> transferOrder) {
        this.transferOrder = transferOrder;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void addUser(long userId) {
        users.add(userId);
    }

    public void removeUser(long userId) {
        users.remove(userId);
    }
}
