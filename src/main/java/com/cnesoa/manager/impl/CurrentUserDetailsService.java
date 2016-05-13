package com.cnesoa.manager.impl;

import com.cnesoa.domain.Person.User;
import com.cnesoa.exceptions.impl.UserNotFoundException;
import com.cnesoa.manager.Person.UserManager;
import com.cnesoa.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 22/04/2016.
 */

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    //autowiring of beans

    @Autowired
    public UserManager userManager;

    /*________________________________________*/

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userManager.getUserByUsername(username);
        if (user == null)
            throw new UserNotFoundException("Username not found");
        else
            return new CurrentUser(user);
    }
}
