package com.cnesoa.manager.Animal.impl;

import com.cnesoa.domain.Animal.FicheMedicale;
import com.cnesoa.manager.Animal.FicheMedicaleManager;
import com.cnesoa.repository.animal.FicheMedicaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 06/04/2016.
 */

@Service
public class FicheMedicaleManagerImpl implements FicheMedicaleManager{

    //Autowiring of beans

    @Autowired
    private FicheMedicaleRepository ficheMedicaleRepository;

    /*________________________________________*/

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
