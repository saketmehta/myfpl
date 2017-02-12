package club.myfpl.resources.dto;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:47 PM
 */
public class UpdateUserDTO {
    private Long   userId;
    private String email;
    private String fullName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
