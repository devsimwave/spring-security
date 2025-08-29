package com.security.session.module.users.dto;

import com.security.session.module.users.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserImpl extends User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private boolean isActivated;

    public UserImpl(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
    }

    public void setDetails(Users users) {
        this.id = users.getId();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.name = users.getName();
        this.isActivated = users.isActivated();
    }
}
