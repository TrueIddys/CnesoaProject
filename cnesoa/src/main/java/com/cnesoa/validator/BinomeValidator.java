package com.cnesoa.validator;

import com.cnesoa.domain.Binome;
import com.cnesoa.exceptions.EleveAlreadyInBinomeException;
import com.cnesoa.exceptions.MissingAttributeException;
import com.cnesoa.exceptions.NullObjectException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Maxime on 10/05/2016.
 */
public class BinomeValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Binome.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Binome binome = (Binome)o;

        if (binome == null){
            throw new NullObjectException("Le binome n'as pas pu être sauvegardé.");
        }
        if (binome.getEleve1().getBinome() != null || binome.getEleve2().getBinome() != null)
        {
            throw new EleveAlreadyInBinomeException("Un des élèves fait déjà parti d'un binome.");
        }
        if (binome.getEleve1() == null || binome.getEleve2() == null){
            throw new MissingAttributeException("Un ou plusieurs des élèves n'ont pas étés choisis.");
        }
    }
}
