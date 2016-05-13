package com.cnesoa.manager.Consultation.impl;

import com.cnesoa.domain.Consultation.Traitement;
import com.cnesoa.manager.Consultation.TraitementManager;
import com.cnesoa.repository.consultation.TraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 08/04/2016.
 */

@Service
public class TraitementManagerImpl implements TraitementManager {

    //Autowiring of beans

    @Autowired
    private TraitementRepository traitementRepository;

    /*__________________________________________*/

    @Override
    public Iterable<Traitement> listAllTraitement() {
        return traitementRepository.findAll();
    }

    @Override
    public Traitement getTraitementById(Long id) {
        return traitementRepository.findOne(id);
    }

    @Override
    public Traitement saveTraitement(Traitement traitement) {
        return traitementRepository.save(traitement);
    }

    @Override
    public void deleteTraitement(Long id) {
        traitementRepository.delete(id);
    }
}
