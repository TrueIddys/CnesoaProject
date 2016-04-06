package com.cnesoa.manager.impl;

import com.cnesoa.domain.FicheMedicale;
import com.cnesoa.manager.FicheMedicaleManager;
import com.cnesoa.repository.FicheMedicaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 06/04/2016.
 */

@Service
public class FicheMedicaleManagerImpl implements FicheMedicaleManager{

    private FicheMedicaleRepository ficheMedicaleRepository;

    @Autowired
    private void setFicheMedicaleRepository(FicheMedicaleRepository ficheMedicaleRepository){
        this.ficheMedicaleRepository = ficheMedicaleRepository;
    }

    @Override
    public Iterable<FicheMedicale> listAllFicheMedicale() {
        return ficheMedicaleRepository.findAll();
    }

    @Override
    public FicheMedicale getFicheMedicaleById(Long id) {
        return ficheMedicaleRepository.findOne(id);
    }

    @Override
    public FicheMedicale saveFicheMedicale(FicheMedicale ficheMedicale) {
        return ficheMedicaleRepository.save(ficheMedicale);
    }

    @Override
    public void deleteFicheMedicale(Long id) {
        ficheMedicaleRepository.delete(id);
    }
}
