package com.cnesoa.validator;

import com.cnesoa.domain.Person.Client;
import com.cnesoa.exceptions.MissingAttributeException;
import com.cnesoa.exceptions.NullObjectException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Maxime on 04/05/2016.
 */
public class ClientValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;

        if (client == null){
            throw new NullObjectException("Le client n'as pas pu être créé.");
        }
        if (client.getNom().isEmpty()){
            throw new MissingAttributeException("Le nom n'as pas été renseigné.");
        }
        if (client.getPrenom().isEmpty()){
            throw new MissingAttributeException("Le prénom n'as pas été renseigné.");
        }
        if (client.getAdresse().getVille().isEmpty()){
            throw new MissingAttributeException("La ville n'as pas été renseignée.");
        }
        if (client.getContact().getTel().isEmpty()){
            throw new MissingAttributeException("Le numéro de téléphone n'as pas été renseigné.");
        }
    }
}
