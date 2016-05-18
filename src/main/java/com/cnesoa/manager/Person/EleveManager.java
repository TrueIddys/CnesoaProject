package com.cnesoa.manager.Person;

import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Consultation.InfosConsult;
import com.cnesoa.domain.Person.Eleve;

/**
 * Created by Maxime on 05/04/2016.
 */
public interface EleveManager {

    Iterable<Eleve> listAllEleve();

    Iterable<Eleve> listEleveWithoutBinome();

    Eleve getEleveById(Long id);

    Eleve saveEleve(Eleve eleve);

    void deleteEleve(Long id);

    Iterable<Consultation> getListConsultations(Long id);

    Iterable<InfosConsult> getListInfosConsult(Long id);

    String moyenneDiag(Long id);

    String moyenneTrait(Long id);

    Boolean checkLienAnimal(Long animalId, Long eleveId);

    Boolean checkLienConsultation(Long consultationId, Long eleveId);

    Boolean checkLienDiagnostic(Long diagnosticId, Long eleveId);

    Boolean checkLienTraitement(Long traitementId, Long eleveId);

    Boolean checkLienInfosConsult(Long infosConsultId, Long eleveId);

    Integer numberOfConsultHours(Long eleveId);

}
