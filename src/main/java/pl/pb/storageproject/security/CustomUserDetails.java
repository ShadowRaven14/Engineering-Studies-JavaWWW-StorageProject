package pl.pb.storageproject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.pb.storageproject.model.Role;
import pl.pb.storageproject.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


public class CustomUserDetails implements UserDetails {


    private User user;

    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities() {
        Set< Role > roles = user.getRoles();

        java.util.List< SimpleGrantedAuthority > authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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
