package com.cnesoa.manager.Consultation.impl;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Person.User;
import com.cnesoa.manager.Animal.AnimalManager;
import com.cnesoa.manager.Consultation.ConsultationManager;
import com.cnesoa.manager.Consultation.InfosConsultManager;
import com.cnesoa.manager.Person.EleveManager;
import com.cnesoa.manager.Person.ProfesseurManager;
import com.cnesoa.manager.Person.UserManager;
import com.cnesoa.repository.consultation.ConsultationRepository;
import com.cnesoa.utils.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 08/04/2016.
 */

@Service
public class ConsultationManagerImpl implements ConsultationManager {

    //Autowiring of beans

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private AnimalManager animalManager;

    @Autowired
    private InfosConsultManager infosConsultManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private EleveManager eleveManager;

    @Autowired
    private ProfesseurManager professeurManager;

    /*________________________________________________*/

    @Override
    public Iterable<Consultation> listAllConsultation() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation getConsultationById(Long id) {
        return consultationRepository.findOne(id);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        if (consultation.getId() == null) {
            consultation.getAnimal().addConsultation(consultation);
            consultation.getInfosConsult().setConsultation(consultation);
        }
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        Consultation consultation = getConsultationById(id);
        Animal animal = animalManager.getAnimalById(consultation.getAnimal().getId());
        animal.removeConsultation(consultation);
        animalManager.saveAnimal(animal);
    }

    /**
     * Transform the consultation into Event class so it can be parsed
     * in the calendar
     * @param consultation
     * @return
     */
    @Override
    public Event toEvent(Consultation consultation) {
        if (consultation == null)
            return null;
        Event e = new Event(
                consultation.getId().toString(),
                consultation.getDateConsultation(),
                consultation.getInfosConsult().getBinome(),
                consultation.getInfosConsult().getProfesseur(),
                consultation.getAnimal(),
                consultation.getAnimal().getClient()
        );
        return e;
    }

    /**
     * Transform a list of consultation into a list of event
     * @param listConsult
     * @return
     */
    @Override
    public Iterable<Event> toListEvent(Iterable<Consultation> listConsult){
        List<Event> listEvent = new ArrayList<>();

        for (Consultation c : listConsult){
            listEvent.add(toEvent(c));
        }

        return listEvent;
    }

    /**
     * return all the consultation related to the current user
     * @return
     */
    @Override
    public Iterable<Consultation> listUserConsultation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) auth.getPrincipal();
        User user = userManager.getUserByUsername(currentUser.getUsername());
        switch (user.getRole()) {
            case ROLE_ADMIN: return listAllConsultation();
            case ROLE_ELEVE: return eleveManager.getListConsultations(user.getId());
            case ROLE_PROF: return professeurManager.getListConsultations(user.getId());
            default : return new ArrayList<>();
        }
    }
}
