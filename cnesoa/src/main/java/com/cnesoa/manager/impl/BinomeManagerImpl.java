package com.cnesoa.manager.impl;

import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Consultation.InfosConsult;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.Consultation.InfosConsultManager;
import com.cnesoa.manager.Person.EleveManager;
import com.cnesoa.repository.BinomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 13/04/2016.
 */

@Service
public class BinomeManagerImpl implements BinomeManager {

    //Autowiring of beans

    @Autowired
    private BinomeRepository binomeRepository;

    @Autowired
    private EleveManager eleveManager;

    @Autowired
    private InfosConsultManager infosConsultManager;

    /*________________________________________*/

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
            binome = binomeRepository.save(binome);
            eleveManager.saveEleve(binome.getEleve2());
            eleveManager.saveEleve(binome.getEleve1());
            return binome;
        }
        else
            return binomeRepository.save(binome);
    }

    @Override
    public void deleteBinome(Long id) {
        getBinomeById(id).getEleve1().setBinome(null);
        getBinomeById(id).getEleve2().setBinome(null);
        eleveManager.saveEleve(getBinomeById(id).getEleve1());
        eleveManager.saveEleve(getBinomeById(id).getEleve2());
        for (InfosConsult i : getBinomeById(id).getInfosConsult()){
            i.setBinome(null);
            infosConsultManager.saveInfosConsult(i);
        }
        binomeRepository.delete(id);
    }

    @Override
    public void removeInfosConsult(Binome binome, InfosConsult infosConsult){
        binome.removeInfosConsult(infosConsult);
        binomeRepository.save(binome);
    }
}
