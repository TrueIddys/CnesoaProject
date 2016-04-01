package com.cnesoa.manager;

import com.cnesoa.domain.Animal;

/**
 * Created by Maxime on 30/03/2016.
 */
public interface AnimalManager {

    Iterable<Animal> listAllAnimal();

    Animal getAnimalById(Long id);

    Animal saveAnimal(Animal animal);

    void deleteAnimal(Long id);

    Animal addAnimal(Long clientId, Animal animal);
}
