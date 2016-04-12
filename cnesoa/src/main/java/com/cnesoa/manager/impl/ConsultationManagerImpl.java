package com.cnesoa.manager.impl;

import com.cnesoa.domain.Consultation;
import com.cnesoa.domain.Event;
import com.cnesoa.manager.ConsultationManager;
import com.cnesoa.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 08/04/2016.
 */

@Service
public class ConsultationManagerImpl implements ConsultationManager {

    private ConsultationRepository consultationRepository;

    private AnimalManagerImpl animalManager;

    private InfosConsultManagerImpl infosConsultManager;


    @Autowired
    private void setConsultationRepository(ConsultationRepository consultationRepository){
        this.consultationRepository = consultationRepository;
    }

    @Autowired
    private void setAnimalManager(AnimalManagerImpl animalManager){
        this.animalManager = animalManager;
    }

    @Autowired
    private void setInfosConsultManager(InfosConsultManagerImpl infosConsultManager){
        this.infosConsultManager = infosConsultManager;
    }

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
        if (consultation.getId() == null){
            consultation.getAnimal().addConsultation(consultation);
            consultation.getInfosConsult().setConsultation(consultation);
            consultationRepository.save(consultation);

//            animalManager.saveAnimal(consultation.getAnimal());
            return consultation;
        }
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.delete(id);
    }

    @Override
    public Event toEvent(Consultation consultation) {
        Event e = new Event();
        e.setId(consultation.getId().toString());
        e.setAnimal(consultation.getAnimal().getNom());
        e.setEleve1(consultation.getInfosConsult().getEleve1().getName());
        e.setEleve2(consultation.getInfosConsult().getEleve2().getName());
        e.setProfesseur(consultation.getInfosConsult().getProfesseur().getName());
        e.setDate(consultation.getDateConsultation().getTime());
        e.setProprio(consultation.getAnimal().getClient().getName());

        return e;
    }

    @Override
    public Iterable<Event> toListEvent(Iterable<Consultation> listConsult){
        List<Event> listEvent = new ArrayList<>();

        for (Consultation c : listConsult){
            listEvent.add(toEvent(c));
        }

        return listEvent;
    }


}
