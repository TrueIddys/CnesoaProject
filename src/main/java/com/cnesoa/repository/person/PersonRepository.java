package com.cnesoa.repository.person;

import com.cnesoa.domain.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 05/04/2016.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
