package com.cnesoa.manager.impl;

import com.cnesoa.domain.Professeur;
import com.cnesoa.manager.ProfesseurManager;
import com.cnesoa.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 06/04/2016.
 */

@Service
public class ProfesseurManagerImpl implements ProfesseurManager{

    private ProfesseurRepository professeurRepository;

    @Autowired
    private void setProfesseurRepository(ProfesseurRepository professeurRepository){
        this.professeurRepository = professeurRepository;
    }

    @Override
    public Iterable<Professeur> listAllProfesseur() {
        return professeurRepository.findAll();
    }

    @Override
    public Professeur getProfesseurById(Long id) {
        return professeurRepository.getOne(id);
    }

    @Override
    public Professeur saveProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    @Override
    public void deleteProfesseur(Long id) {
        professeurRepository.delete(id);
    }
}
