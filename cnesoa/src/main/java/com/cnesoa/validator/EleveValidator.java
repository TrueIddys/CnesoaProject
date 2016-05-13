package com.cnesoa.validator;

import com.cnesoa.domain.Person.Eleve;
import com.cnesoa.exceptions.MissingAttributeException;
import com.cnesoa.exceptions.NullObjectException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Maxime on 04/05/2016.
 * Used to validate a student.
 */
public class EleveValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return Eleve.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Eleve eleve = (Eleve)o;

        if (eleve == null){
            throw new NullObjectException("L'élève n'as pas pu être créé.");
        }
        if (eleve.getNom().isEmpty())
        {
            throw new MissingAttributeException("Le nom n'as pas été renseigné."    );
        }
        if (eleve.getPrenom().isEmpty()){
            throw new MissingAttributeException("Le prénom n'as pas été renseigné.");
        }

    }
}
