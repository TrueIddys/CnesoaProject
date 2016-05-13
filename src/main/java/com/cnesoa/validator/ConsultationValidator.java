package com.cnesoa.validator;

import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.exceptions.MissingAttributeException;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.exceptions.WrongDateException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Maxime on 04/05/2016.
 */
public class ConsultationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Consultation.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Consultation consultation = (Consultation)o;

        if (consultation == null){
            throw new NullObjectException("La consultation n'as pas pu être créée.");
        }
        if (consultation.getDateConsultation() == null){
            throw new MissingAttributeException("La date n'as pas été renseignée.");
        }
        LocalDate lDate = consultation.getDateConsultation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (lDate.isBefore(LocalDate.now()))
        {
            throw new WrongDateException("La date ne peut pas être inférieur à la date actuelle.");
        }

        if (consultation.getTypeConsultation() == null || consultation.getTypeConsultation().isEmpty()){
            throw new MissingAttributeException("Le type de consultation n'as pas été renseigné.");
        }

    }
}
