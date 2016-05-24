package com.cnesoa.manager.Consultation;

import com.cnesoa.domain.Consultation.Traitement;

/**
 * Created by Maxime on 08/04/2016.
 */
public interface TraitementManager {

    Iterable<Traitement> listAllTraitement();

    Traitement getTraitementById(Long id);

    Traitement saveTraitement(Traitement traitement);

    void deleteTraitement(Long id);
}
