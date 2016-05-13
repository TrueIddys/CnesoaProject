package com.cnesoa.manager.Person.impl;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Person.Client;
import com.cnesoa.manager.Animal.AnimalManager;
import com.cnesoa.manager.Person.ClientManager;
import com.cnesoa.repository.animal.AnimalRepository;
import com.cnesoa.repository.person.ClientRepository;
import com.cnesoa.utils.Cotisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 */
@Service
public class ClientManagerImpl implements ClientManager {

    //Autowiring of beans

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalManager animalManager;

    /*_________________________*/

    @Override
    public Iterable<Client> listAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public Client saveClient(Client client) {
        if (client.getCotisation() == Cotisation.tarifHaut &&
                client.getDebutCotisation() == null)
            client.setDebutCotisation(Date.from(Instant.now()));
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.delete(id);
    }

    @Override
    public void removeAnimal(Client client, Animal animal){
        client.removeAnimal(animal);
        clientRepository.save(client);
    }
}
