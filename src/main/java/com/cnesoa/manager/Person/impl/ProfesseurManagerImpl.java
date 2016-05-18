package com.cnesoa.manager.Person.impl;

import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Consultation.InfosConsult;
import com.cnesoa.domain.Person.Professeur;
import com.cnesoa.manager.Consultation.InfosConsultManager;
import com.cnesoa.manager.Person.PersonManager;
import com.cnesoa.manager.Person.ProfesseurManager;
import com.cnesoa.repository.person.ProfesseurRepository;
import com.cnesoa.utils.Role;
import com.cnesoa.utils.UserCredentialsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 06/04/2016.
 */

@Service
public class ProfesseurManagerImpl implements ProfesseurManager{

    //Autowiring of beans

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private InfosConsultManager infosConsultManager;

    @Autowired
    private PersonManager personManager;

    @Autowired
    private UserCredentialsGenerator ucg;

    /*____________________________________*/

    @Override
    public Iterable<Professeur> listAllProfesseur() {
        return professeurRepository.findAll();
    }

    @Override
    public Professeur getProfesseurById(Long id) {
        Professeur professeur = professeurRepository.findOne(id);
        return professeur;
    }

    @Override
    public Professeur saveProfesseur(Professeur professeur) {

        if (professeur.getPasswordHash() == null || professeur.getUsername() == null
                || professeur.getPasswordHash().isEmpty() || professeur.getUsername().isEmpty()) {
            professeur.setUsername(ucg.generateUsername(professeur));
            professeur.setPasswordHash(ucg.generatePassword());
        }
        if (professeur.getCode() == null ||professeur.getCode().isEmpty()){
            professeur.setCode(ucg.generateProfessorCode());
        }
        if (professeur.getId() == null) {
            professeur.setRole(Role.ROLE_PROF);
            professeur.getContact().setPerson(professeur);
        }
        return professeurRepository.save(professeur);
    }

    @Override
    public void deleteProfesseur(Long id) {
        Professeur professeur = professeurRepository.findOne(id);
        for (InfosConsult i : professeur.getInfosConsult()){
            i.setProfesseur(null);
            infosConsultManager.saveInfosConsult(i);
        }
        professeurRepository.delete(id);
    }

    @Override
    public Iterable<Consultation> getListConsultations(Long profId) {
        Professeur prof = getProfesseurById(profId);
        List<Consultation> listConsults = new ArrayList<>();
        for (InfosConsult i : prof.getInfosConsult()){
            listConsults.add(i.getConsultation());
        }

        return listConsults;
    }

    /*Check data*/

    /**
     * return true or false whether the professor is related to the animal
     * @param animalId
     * @param profId
     * @return
     */
    @Override
    public Boolean checkLienAnimal(Long animalId, Long profId) {
        Professeur prof = professeurRepository.findOne(profId);
        for (InfosConsult i : prof.getInfosConsult()){
            if (i.getConsultation().getAnimal().getId() == animalId){
                return true;
            }
        }
        return false;
    }

    /**
     * return true or false whether the professor is related to the consultation
     * @param consultationId
     * @param profId
     * @return
     */
    @Override
    public Boolean checkLienConsultation(Long consultationId, Long profId){
        Professeur prof = professeurRepository.findOne(profId);
        for (InfosConsult i : prof.getInfosConsult()){
            if (i.getConsultation().getId() == consultationId)
                return  true;
        }
        return  false;
    }

    /**
     * return true or false whether the professor is related to the diagnostic
     * @param diagnosticId
     * @param profId
     * @return
     */
    @Override
    public Boolean checkLienDiagnostic(Long diagnosticId, Long profId) {
        Professeur prof = professeurRepository.findOne(profId);
        for (InfosConsult i : prof.getInfosConsult()){
            if (i.getConsultation().getDiagnostic().getId() == diagnosticId)
                return true;
        }
        return false;
    }

    /**
     * return true or false whether the professor is related to the treatment
     * @param traitementId
     * @param profId
     * @return
     */
    @Override
    public Boolean checkLienTraitement(Long traitementId, Long profId) {
        Professeur prof = professeurRepository.findOne(profId);
        for (InfosConsult i : prof.getInfosConsult()){
            if (i.getConsultation().getTraitement().getId() == traitementId)
                return true;
        }
        return false;
    }

    /**
     * return true or false whether the professor is related to the consultation
     * @param infosConsultId
     * @param profId
     * @return
     */
    @Override
    public Boolean checkLienInfosConsult(Long infosConsultId, Long profId){
        Professeur prof = professeurRepository.findOne(profId);
        for (InfosConsult i : prof.getInfosConsult()){
            if (i.getConsultation().getInfosConsult().getId() == infosConsultId)
                return true;
        }
        return false;
    }
}
