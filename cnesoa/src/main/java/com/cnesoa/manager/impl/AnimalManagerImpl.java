package com.cnesoa.manager.impl;

import com.cnesoa.domain.Animal;
import com.cnesoa.domain.Client;
import com.cnesoa.domain.FicheMedicale;
import com.cnesoa.manager.AnimalManager;
import com.cnesoa.manager.ClientManager;
import com.cnesoa.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 30/03/2016.
 */
@Service
public class AnimalManagerImpl implements AnimalManager {

    private AnimalRepository animalRepository;

    private ClientManager clientManager;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    @Autowired
    public void setClientManager(ClientManager clientManager){
        this.clientManager = clientManager;
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
        if (animal.getId() == null) {
            animal.getClient().addAnimal(animal);
            animal.setFicheMedicale(new FicheMedicale(animal));
            animalRepository.save(animal);
            clientManager.saveClient(animal.getClient());
            return animal;
        }
        else
            return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        Animal animal = animalRepository.findOne(id);
        Client client = clientManager.getClientById(animal.getClient().getId());
        client.removeAnimal(animal);
        clientManager.saveClient(client);
        animalRepository.delete(id);
    }

    @Override
    public Animal addAnimal(Animal animal) {
        animal.getClient().addAnimal(animal);
        animal.setFicheMedicale(new FicheMedicale(animal));
        animalRepository.save(animal);
        clientManager.saveClient(animal.getClient());
        return animal;
    }

}
