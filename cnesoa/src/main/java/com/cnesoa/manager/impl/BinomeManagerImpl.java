package com.cnesoa.manager.impl;

import com.cnesoa.domain.Binome;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.EleveManager;
import com.cnesoa.repository.BinomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 13/04/2016.
 */

@Service
public class BinomeManagerImpl implements BinomeManager {

    private BinomeRepository binomeRepository;

    private EleveManager eleveManager;

    @Autowired
    private void setEleveManager(EleveManager eleveManager){
        this.eleveManager = eleveManager;
    }

    @Autowired
    private void setBinomeRepository(BinomeRepository binomeRepository){
        this.binomeRepository = binomeRepository;
    }

    @Override
    public Iterable<Binome> listAllBinome() {
        return binomeRepository.findAll();
    }

    @Override
    public Binome getBinomeById(Long id) {
        return binomeRepository.findOne(id);
    }

    @Override
    public Binome saveBinome(Binome binome) {

        if (binome.getId() == null)
        {
            binome.getEleve1().setBinome(binome);
            binome.getEleve2().setBinome(binome);
            binomeRepository.save(binome);
            eleveManager.saveEleve(binome.getEleve2());
            eleveManager.saveEleve(binome.getEleve1());
        }

        return binomeRepository.save(binome);
    }

    @Override
    public void deleteBinome(Long id) {
        binomeRepository.delete(id);
    }
}
