package com.cnesoa.domain.Animal;

import com.cnesoa.utils.Generator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 10/05/2016.
 */
public class AnimalTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName(){
        new Animal(null, "Chien", "Male");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullRace(){
        new Animal("Rex", null, "Male");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullSexe(){
        new Animal("Rex", "Chien", null);
    }

    @Test
    public void shouldInstantiate(){
        String name = Generator.createName();
        String race = "Chien";
        String sexe = "Male";
        Animal animal = new Animal(name, race, sexe);
        assertThat(animal.getNom()).isEqualTo(name);
        assertThat(animal.getRace()).isEqualTo(race);
        assertThat(animal.getSexe()).isEqualTo(sexe);
    }

    @Test
    public void shouldBeEqualById(){
        Animal animal = new Animal();
        animal.setId(1L);
        Animal animal2 = new Animal();
        animal2.setId(1L);
        assertThat(animal).isEqualTo(animal2);
    }

}
