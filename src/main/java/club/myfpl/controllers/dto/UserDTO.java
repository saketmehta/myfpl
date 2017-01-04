package club.myfpl.controllers.dto;

import club.myfpl.model.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:47 PM
 */
public class UserDTO {
    @NotBlank
    private String email;
    private String password;
    @NotBlank
    private String fullName;

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

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        if (StringUtils.isNotEmpty(password)) {
            user.setPassword(password);
        }
        user.setFullName(fullName);
        return user;
    }
}
