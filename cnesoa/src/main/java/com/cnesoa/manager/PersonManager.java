package com.cnesoa.manager;

import com.cnesoa.domain.Person;

/**
 * Created by Maxime on 05/04/2016.
 */
public interface PersonManager {

    Iterable<Person> listAllPerson();

    Person getPersonById(Long id);

    Person savePerson(Person person);

    void deletePerson(Long id);
}
