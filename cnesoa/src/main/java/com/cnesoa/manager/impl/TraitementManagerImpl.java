package com.cnesoa.manager.impl;

import com.cnesoa.domain.Traitement;
import com.cnesoa.manager.TraitementManager;
import com.cnesoa.repository.TraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 08/04/2016.
 */

@Service
public class TraitementManagerImpl implements TraitementManager {

    private TraitementRepository traitementRepository;

    @Autowired
    private void setTraitementRepository(TraitementRepository traitementRepository){
        this.traitementRepository = traitementRepository;
    }


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
