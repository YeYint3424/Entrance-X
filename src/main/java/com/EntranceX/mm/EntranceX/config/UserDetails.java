package com.EntranceX.mm.EntranceX.config;


import com.EntranceX.mm.EntranceX.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    @Autowired
    private Admin admin;
    @Autowired
    private Event event;
    @Autowired
    private Organizer organizer;
    @Autowired
    private TicketOrder_History orderHistory;
    @Autowired
    private User user;
    @Autowired
    private WatchLater watchLater;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
