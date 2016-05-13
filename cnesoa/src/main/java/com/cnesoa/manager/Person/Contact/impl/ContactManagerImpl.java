package com.cnesoa.manager.Person.Contact.impl;

import com.cnesoa.domain.Person.Contact.Contact;
import com.cnesoa.manager.Person.Contact.ContactManager;
import com.cnesoa.repository.person.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 29/04/2016.
 */

@Service
public class ContactManagerImpl implements ContactManager{

    //Autowiring of beans

    @Autowired
    private ContactRepository contactRepository;

    /*________________________________________*/

    @Override
    public Iterable<Contact> listAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.delete(id);
    }

    /*Check data*/

    @Override
    public Boolean checkUniqueMail(String mail) {
        if (contactRepository.findByMail(mail) != null){
            return false;
        }
        else
            return true;
    }

    @Override
    public Boolean checkUniqueTel(String tel) {
        if (contactRepository.findByTel(tel) != null){
            return false;
        }
        else
            return true;
    }
}
