package com.cnesoa.manager.Person;

import com.cnesoa.domain.Person.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Maxime on 22/04/2016.
 */


public interface UserManager {

    Iterable<User> listAllUser();

    User getUserById(Long id);

    User getUserByUsername(String username) throws UsernameNotFoundException;

    User saveUser(User user);

    Boolean checkUniqueUsername(String username);

    void deleteUser(Long id);
}
