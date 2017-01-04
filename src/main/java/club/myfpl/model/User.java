package club.myfpl.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Saket on 15/09/16.
 */
@Document
public class User {
    public static final String USER_ID_KEY = "userId";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";
    public static final String FULL_NAME_KEY = "fullName";
    public static final String ROLES_KEY = "roles";

    private String id;
    private long userId;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String fullName;
    private List<String> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String rolesAsCommaSeparatedString() {
        return this.roles.stream().collect(Collectors.joining(","));
    }
}
