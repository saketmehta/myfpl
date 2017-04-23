package club.myfpl.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

/**
 * @author Saket
 * @since 23/04/17.
 */
@Document
public class League {

    private String              id;
    private String              name;
    private String              adminUserId;
    private Long                capacity;
    private Set<String>         users;
    private Boolean             isLocked;
    private String              transferTurnUserId;
    private String              firstTransferUserId;
    private Map<String, String> transferOrder;
    @Indexed(unique = true)
    private String              inviteCode;

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

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public String getTransferTurnUserId() {
        return transferTurnUserId;
    }

    public void setTransferTurnUserId(String transferTurnUserId) {
        this.transferTurnUserId = transferTurnUserId;
    }

    public String getFirstTransferUserId() {
        return firstTransferUserId;
    }

    public void setFirstTransferUserId(String firstTransferUserId) {
        this.firstTransferUserId = firstTransferUserId;
    }

    public Map<String, String> getTransferOrder() {
        return transferOrder;
    }

    public void setTransferOrder(Map<String, String> transferOrder) {
        this.transferOrder = transferOrder;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
