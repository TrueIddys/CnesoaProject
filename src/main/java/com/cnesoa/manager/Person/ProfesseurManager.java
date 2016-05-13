package com.cnesoa.manager.Person;

import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Person.Professeur;

/**
 * Created by Maxime on 06/04/2016.
 */
public interface ProfesseurManager {

    Iterable<Professeur> listAllProfesseur();

    Professeur getProfesseurById(Long id);

    Professeur saveProfesseur(Professeur professeur);

    void deleteProfesseur(Long id);

    Iterable<Consultation> getListConsultations(Long profId);

    Boolean checkLienAnimal(Long animalId, Long profId);

    Boolean checkLienConsultation(Long consultationId, Long profId);

    Boolean checkLienDiagnostic(Long diagnosticId, Long profId);

    Boolean checkLienTraitement(Long traitementId, Long profId);

    Boolean checkLienInfosConsult(Long infosConsultId, Long profId);
}
