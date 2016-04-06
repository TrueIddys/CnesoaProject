package com.cnesoa.manager.impl;

import com.cnesoa.domain.Eleve;
import com.cnesoa.manager.EleveManager;
import com.cnesoa.repository.EleveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 05/04/2016.
 */

@Service
public class EleveManagerImpl implements EleveManager {

    private EleveRepository eleveRepository;

    @Autowired
    private void setEleveRepository(EleveRepository eleveRepository){
        this.eleveRepository = eleveRepository;
    }

    @Override
    public Iterable<Eleve> listAllEleve() {
        return this.eleveRepository.findAll();
    }

    @Override
    public Eleve getEleveById(Long id) {
        return this.eleveRepository.findOne(id);
    }

    @Override
    public Eleve saveEleve(Eleve eleve) {
        return this.eleveRepository.save(eleve);
    }

    @Override
    public void deleteEleve(Long id) {
        this.eleveRepository.delete(id);
    }
}
