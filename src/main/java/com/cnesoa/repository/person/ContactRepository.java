package com.cnesoa.repository.person;

import com.cnesoa.domain.Person.Contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 29/04/2016.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByTel(String tel);

    Contact findByMail(String mail);
}
