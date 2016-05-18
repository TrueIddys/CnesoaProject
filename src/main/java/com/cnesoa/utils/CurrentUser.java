package com.cnesoa.utils;

import com.cnesoa.domain.Person.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by Maxime on 22/04/2016.
 * Current user
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(com.cnesoa.domain.Person.User user) {
        super(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }
}
