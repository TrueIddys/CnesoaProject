package com.cnesoa.manager.Person.impl;

import com.cnesoa.domain.Person.Person;
import com.cnesoa.manager.Person.PersonManager;
import com.cnesoa.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 05/04/2016.
 */

@Service
public class PersonManagerImpl implements PersonManager {

    //Autowiring of beans

    @Autowired
    private PersonRepository personRepository;

    /*___________________________________*/

    @Override
    public Iterable<Person> listAllPerson() {
        return this.personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return this.personRepository.findOne(id);
    }

    @Override
    public Person savePerson(Person person) {
        return this.personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        this.personRepository.delete(id);
    }
}
