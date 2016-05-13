package com.cnesoa.manager.Person;

import com.cnesoa.domain.Person.Person;

/**
 * Created by Maxime on 05/04/2016.
 */
public interface PersonManager {

    Iterable<Person> listAllPerson();

    Person getPersonById(Long id);

    Person savePerson(Person person);

    void deletePerson(Long id);
}
