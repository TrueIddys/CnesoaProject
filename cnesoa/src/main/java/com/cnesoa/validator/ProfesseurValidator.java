package com.cnesoa.validator;

import com.cnesoa.domain.Person.Professeur;
import com.cnesoa.exceptions.MissingAttributeException;
import com.cnesoa.exceptions.NullObjectException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Maxime on 04/05/2016.
 */
public class ProfesseurValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Professeur.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Professeur professeur = (Professeur)o;

        if (professeur == null){
            throw new NullObjectException("Le professeur n'as pas pu être créé.");
        }
        if (professeur.getNom().isEmpty())
        {
            throw new MissingAttributeException("Le nom n'as pas été renseigné.");
        }
        if (professeur.getPrenom().isEmpty()){
            throw new MissingAttributeException("Le prénom n'as pas été renseigné.");
        }

    }
}
