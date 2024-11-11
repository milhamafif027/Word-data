package com.sibkm.serverapp.model;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sibkm.serverapp.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppUserDetail implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
          String roles = "ROLE_" + role.getName().toUpperCase();
          authorities.add(new SimpleGrantedAuthority(roles));

          role.getPrivileges().forEach(privilege -> {
              String privileges = privilege.getName().toUpperCase();
              authorities.add(new SimpleGrantedAuthority(privileges));
            });
        });
  
      return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
}
