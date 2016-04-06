package com.cnesoa.manager.impl;

import com.cnesoa.domain.Person;
import com.cnesoa.manager.PersonManager;
import com.cnesoa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 05/04/2016.
 */

@Service
public class PersonManagerImpl implements PersonManager {

    private PersonRepository personRepository;

    @Autowired
    private void setPersonRepository(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

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
