package com.cnesoa.repository.person;

import com.cnesoa.domain.Person.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 22/04/2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
