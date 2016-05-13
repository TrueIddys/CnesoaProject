package com.cnesoa.manager.Person.Contact;

import com.cnesoa.domain.Person.Contact.Adresse;

/**
 * Created by Maxime on 20/04/2016.
 */
public interface AdresseManager {

    Iterable<Adresse> listAllAdresse();

    Adresse getAdresseById(Long id);

    Adresse saveAdresse(Adresse adresse);

    void deleteAdresse(Long id);
}
