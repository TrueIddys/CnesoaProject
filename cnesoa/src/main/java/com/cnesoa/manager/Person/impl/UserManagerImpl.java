package com.cnesoa.manager.Person.impl;

import com.cnesoa.domain.Person.User;
import com.cnesoa.manager.Person.UserManager;
import com.cnesoa.repository.person.UserRepository;
import com.cnesoa.utils.Role;
import com.cnesoa.utils.UserCredentialsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 22/04/2016.
 */

@Service
public class UserManagerImpl implements UserManager {

    //Autowiring of beans

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsGenerator ucg;

    /*_________________________________*/

    @Override
    public Iterable<User> listAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveUser(User user) {
        if (user.getPassword() == null || user.getUsername() == null
                || user.getPassword().isEmpty() || user.getUsername().isEmpty()) {
            user.setUsername(ucg.generateUsername(user));
            user.setPassword(ucg.generatePassword());
        }
        user.setRole(Role.ROLE_ADMIN);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    /*Check data*/

    @Override
    public Boolean checkUniqueUsername(String username){
        if (userRepository.findByUsername(username) != null){
            return false;
        }
        return true;
    }
}
