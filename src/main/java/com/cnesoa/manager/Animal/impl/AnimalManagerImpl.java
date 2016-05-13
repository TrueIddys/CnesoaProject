package com.cnesoa.manager.Animal.impl;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Animal.FicheMedicale;
import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.manager.Animal.AnimalManager;
import com.cnesoa.manager.Person.ClientManager;
import com.cnesoa.repository.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Maxime on 30/03/2016.
 */
@Service
public class AnimalManagerImpl implements AnimalManager {

    //Autowiring of beans

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClientManager clientManager;

    /*________________________________________*/

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
        }
        return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        Animal animal = animalRepository.findOne(id);
        clientManager.removeAnimal(animal.getClient(), animal);
    }

    @Override
    public Binome lastBinome(Long id){
        List<Consultation> listConsult = getAnimalById(id).getConsultations();
        Collections.sort(listConsult);
        return listConsult.get(listConsult.size()-1).getInfosConsult().getBinome();
    }
}
