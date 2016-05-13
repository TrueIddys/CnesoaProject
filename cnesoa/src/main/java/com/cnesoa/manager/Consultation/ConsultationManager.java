package com.cnesoa.manager.Consultation;

import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.utils.Event;

/**
 * Created by Maxime on 08/04/2016.
 */
public interface ConsultationManager {

    Iterable<Consultation> listAllConsultation();

    Consultation getConsultationById(Long id);

    Consultation saveConsultation(Consultation consultation);

    void deleteConsultation(Long id);

    Event toEvent(Consultation consultation);

    Iterable<Event> toListEvent(Iterable<Consultation> listConsult);

    Iterable<Consultation> listUserConsultation();
}
