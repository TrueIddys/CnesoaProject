package com.cnesoa.manager.Person.impl;

import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Consultation.InfosConsult;
import com.cnesoa.domain.Person.Eleve;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.Person.EleveManager;
import com.cnesoa.repository.person.EleveRepository;
import com.cnesoa.utils.DateCalculator;
import com.cnesoa.utils.Role;
import com.cnesoa.utils.UserCredentialsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 05/04/2016.
 */

@Service
public class EleveManagerImpl implements EleveManager {

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private BinomeManager binomeManager;

    @Autowired
    private UserCredentialsGenerator ucg;

    @Override
    public Iterable<Eleve> listAllEleve() {
        return this.eleveRepository.findAll();
    }

    @Override
    public Iterable<Eleve> listEleveWithoutBinome(){
        Iterable<Eleve> allEleve = listAllEleve();
        List<Eleve> eleveWithoutBinome = new ArrayList<>();
        for (Eleve e : allEleve){
            if (e.getBinome() == null)
                eleveWithoutBinome.add(e);
        }
        return eleveWithoutBinome;
    }

    @Override
    public Eleve getEleveById(Long id) {
        return this.eleveRepository.findOne(id);
    }

    @Override
    public Eleve saveEleve(Eleve eleve) {
        if ( eleve.getPassword() == null || eleve.getUsername() == null ||
                eleve.getPassword().isEmpty() || eleve.getUsername().isEmpty()) {
            eleve.setUsername(ucg.generateUsername(eleve));
            eleve.setPassword(ucg.generatePassword());
        }
        if (eleve.getId() == null)
            eleve.setRole(Role.ROLE_ELEVE);
        return eleveRepository.save(eleve);
    }

    @Override
    public void deleteEleve(Long id) {
        Binome binome = getEleveById(id).getBinome();
        if (binome != null)
            binomeManager.deleteBinome(binome.getId());
        this.eleveRepository.delete(id);
    }

    @Override
    public Iterable<Consultation> getListConsultations(Long eleveId) {
        Binome binome = getEleveById(eleveId).getBinome();
        List<Consultation> listConsult = new ArrayList<>();
        if (binome != null){
            for (InfosConsult i : binome.getInfosConsult()){
                listConsult.add(i.getConsultation());
            }
        }
        return listConsult;
    }

    /*Check data*/

    /**
     * return true or false whether the student is related to the animal
     * @param animalId
     * @param eleveId
     * @return
     */
    @Override
    public Boolean checkLienAnimal(Long animalId, Long eleveId){
        for (Consultation c : getListConsultations(eleveId)){
            if (c.getAnimal().getId() == animalId)
                return true;
        }
        return false;
    }

    /**
     * return true or false whether the student is related to the consultation
     * @param consultationId
     * @param eleveId
     * @return
     */
    @Override
    public Boolean checkLienConsultation(Long consultationId, Long eleveId){
        for (Consultation c : getListConsultations(eleveId)){
            if (c.getId() == consultationId)
                return true;
        }
        return false;
    }

    /**
     * Return true or false whether the student is related to the diagnostic
     * @param diagnosticId
     * @param eleveId
     * @return
     */
    @Override
    public Boolean checkLienDiagnostic(Long diagnosticId, Long eleveId) {
        for (Consultation c : getListConsultations(eleveId)){
            if (c.getDiagnostic().getId() == diagnosticId)
                return true;
        }
        return false;
    }

    /**
     * Return true or false whether this student is related to the treatment
     * @param traitementId
     * @param eleveId
     * @return
     */
    @Override
    public Boolean checkLienTraitement(Long traitementId, Long eleveId) {
        for (Consultation c : getListConsultations(eleveId)){
            if (c.getTraitement().getId() == traitementId){
                return true;
            }
        }
        return false;
    }

    /**
     * Return true or false whether the student is related to this consultation
     * @param infosConsultId
     * @param eleveId
     * @return
     */
    @Override
    public Boolean checkLienInfosConsult(Long infosConsultId, Long eleveId){
        for (Consultation c : getListConsultations(eleveId)){
            if (c.getInfosConsult().getId() == infosConsultId)
                return true;
        }
        return false;
    }


    /**
     * Return the number of hours spent doing consultation in the current school year
     * @param eleveId
     * @return
     */
    @Override
    public Integer numberOfConsultHours(Long eleveId){
        LocalDate start = DateCalculator.getStartOfTheYear();
        LocalDate end = DateCalculator.getEndOfTheYar();
        int cpt = 0;
        for (Consultation c : getListConsultations(eleveId)){
            LocalDate cDate = Instant.ofEpochMilli(c.getDateConsultation().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            if (cDate.isBefore(end) || cDate.isAfter(start)){
                cpt++;
            }
        }
        return cpt;
    }


}
