package com.cnesoa.manager.impl;

import com.cnesoa.domain.Animal;
import com.cnesoa.domain.Client;
import com.cnesoa.manager.AnimalManager;
import com.cnesoa.manager.ClientManager;
import com.cnesoa.repository.AnimalRepository;
import com.cnesoa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 30/03/2016.
 */
@Service
public class ClientManagerImpl implements ClientManager {

    private ClientRepository clientRepository;

    private AnimalRepository animalRepository;

    private AnimalManager animalManager;

    @Autowired
    public void setAnimalManager(AnimalManager animalManager){
        this.animalManager = animalManager;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

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
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.delete(id);
    }

    @Override
    public Animal addAnimal(Long clientId, Long animalId) {
        Client client = clientRepository.findOne(clientId);
        Animal animal = animalRepository.findOne(animalId);

        client.addAnimal(animal);
        clientRepository.save(client);
        animalRepository.save(animal);

        return animal;
    }
}
