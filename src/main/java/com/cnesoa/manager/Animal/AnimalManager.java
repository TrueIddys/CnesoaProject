package com.cnesoa.manager.Animal;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Binome;

/**
 * Created by Maxime on 30/03/2016.
 */
public interface AnimalManager {

    Iterable<Animal> listAllAnimal();

    Animal getAnimalById(Long id);

    Animal saveAnimal(Animal animal);

    void deleteAnimal(Long id);

    Binome lastBinome(Long id);
}
