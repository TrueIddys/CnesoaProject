package com.cnesoa.manager.impl;

import com.cnesoa.domain.Animal;
import com.cnesoa.domain.Client;
import com.cnesoa.manager.AnimalManager;
import com.cnesoa.repository.AnimalRepository;
import com.cnesoa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 30/03/2016.
 */
@Service
public class AnimalManagerImpl implements AnimalManager {

    private AnimalRepository animalRepository;

    private ClientRepository clientRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }


    @Override
    public Iterable<Animal> listAllAnimal() {
        return animalRepository.findAll();
    }

    @Override
    public Animal getAnimalById(Long id) {
        return animalRepository.findOne(id);
    }

    @Override
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        animalRepository.delete(id);
    }

    @Override
    public Animal addAnimal(Long clientId, Animal animal) {
        Client client = clientRepository.findOne(clientId);

        client.addAnimal(animal);
        animal.setClient(client);
        animalRepository.save(animal);
        clientRepository.save(client);


        return animal;
    }

}
