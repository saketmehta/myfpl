package club.myfpl.security.model;

import club.myfpl.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * User: Saket
 * Date: 27/12/16
 * Time: 4:10 PM
 */
public class AuthenticatedUser extends User implements UserDetails {

    public AuthenticatedUser(User user) {
        setId(user.getId());
        setUserId(user.getUserId());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setFullName(user.getFullName());
        setRoles(user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities;
        if (CollectionUtils.isEmpty(this.getRoles())) {
            authorities = AuthorityUtils.createAuthorityList("USER");
        } else {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(this.rolesAsCommaSeparatedString());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
