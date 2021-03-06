package ru.n5g.birthdays.core.server.bean;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {
  private User user;

  public AppUserDetails(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    GrantedAuthority authority = new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return user.getRole().getCode().toString();
      }
    };
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    grantedAuthorities.add(authority);
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getLogin();
  }

  public User getUser() {
    return user;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;  //TODO: implement this method
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;  //TODO: implement this method
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;  //TODO: implement this method
  }

  @Override
  public boolean isEnabled() {
    return true;  //TODO: implement this method
  }
}
