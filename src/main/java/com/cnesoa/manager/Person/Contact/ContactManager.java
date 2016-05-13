package com.cnesoa.manager.Person.Contact;

import com.cnesoa.domain.Person.Contact.Contact;

/**
 * Created by Maxime on 29/04/2016.
 */
public interface ContactManager {

    Iterable<Contact> listAllContact();

    Contact getContactById(Long id);

    Contact saveContact(Contact contact);

    void deleteContact(Long id);

    Boolean checkUniqueMail(String mail);

    Boolean checkUniqueTel(String tel);
}
