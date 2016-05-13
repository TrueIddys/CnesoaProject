package com.cnesoa.manager.Person.Contact.impl;

import com.cnesoa.domain.Person.Contact.Adresse;
import com.cnesoa.manager.Person.Contact.AdresseManager;
import com.cnesoa.manager.Person.ClientManager;
import com.cnesoa.repository.person.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 20/04/2016.
 */

@Service
public class AdresseManagerImpl implements AdresseManager {

    //Autowiring of beans

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private ClientManager clientManager;

    /*___________________________________*/

    @Override
    public Iterable<Adresse> listAllAdresse() {
        return adresseRepository.findAll();
    }

    @Override
    public Adresse getAdresseById(Long id) {
        return adresseRepository.findOne(id);
    }

    @Override
    public Adresse saveAdresse(Adresse adresse) {
        if (adresse.getId() == null){
            adresse.getClient().setAdresse(adresse);
            adresse = adresseRepository.save(adresse);
            clientManager.saveClient(adresse.getClient());
            return adresse;
        }
        else
            return adresseRepository.save(adresse);
    }

    @Override
    public void deleteAdresse(Long id) {
        adresseRepository.delete(id);
    }
}
