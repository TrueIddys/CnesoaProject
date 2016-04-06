package com.cnesoa.manager;

import com.cnesoa.domain.Professeur;

/**
 * Created by Maxime on 06/04/2016.
 */
public interface ProfesseurManager {

    Iterable<Professeur> listAllProfesseur();

    Professeur getProfesseurById(Long id);

    Professeur saveProfesseur(Professeur professeur);

    void deleteProfesseur(Long id);
}
