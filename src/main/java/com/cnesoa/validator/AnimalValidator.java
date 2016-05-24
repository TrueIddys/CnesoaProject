package com.cnesoa.validator;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.exceptions.MissingAttributeException;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.exceptions.WrongDateException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Instant;
import java.util.Date;

/**
 * Created by Maxime on 10/05/2016.
 */
public class AnimalValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Animal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Animal animal = (Animal)o;

        if (animal == null){
            throw new NullObjectException("L'animal n'as pas pu être enregistré.");
        }
        if (animal.getNom().isEmpty()){
            throw new MissingAttributeException("Le nom n'as pas été renseigné.");
        }
        if (animal.getRace().isEmpty()){
            throw new MissingAttributeException("La race de l'animal n'as pas été renseignée.");
        }
        if (animal.getDateNaissance().after(Date.from(Instant.now()))){
            throw new WrongDateException("La date de naissance ne doit pas être supérieure à la date d'aujourd'hui");
        }
    }
}
