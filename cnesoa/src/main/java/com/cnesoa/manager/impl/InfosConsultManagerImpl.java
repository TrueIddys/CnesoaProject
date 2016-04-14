package com.cnesoa.manager.impl;

import com.cnesoa.domain.InfosConsult;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.InfosConsultManager;
import com.cnesoa.manager.ProfesseurManager;
import com.cnesoa.repository.InfosConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 07/04/2016.
 */

@Service
public class InfosConsultManagerImpl implements InfosConsultManager{

    private InfosConsultRepository infosConsultRepository;

    private BinomeManager binomeManager;

    private ProfesseurManager professeurManager;

    @Autowired
    private void setProfesseurManager(ProfesseurManager professeurManager){
        this.professeurManager = professeurManager;
    }

    @Autowired
    private void setBinomeManager(BinomeManager binomeManager){
        this.binomeManager = binomeManager;
    }

    @Autowired
    private void setInfosConsultRepository(InfosConsultRepository infosConsultRepository){
        this.infosConsultRepository = infosConsultRepository;
    }

    @Override
    public Iterable<InfosConsult> listAllInfosConsult() {
        return infosConsultRepository.findAll();
    }

    @Override
    public InfosConsult getInfosConsultById(Long id) {
        return infosConsultRepository.findOne(id);
    }

    @Override
    public InfosConsult saveInfosConsult(InfosConsult infosConsult) {
        if (infosConsult.getId() == null){
            infosConsult.getBinome().addInfosConsult(infosConsult);
            infosConsult.getProfesseur().addInfosConsult(infosConsult);
            infosConsultRepository.save(infosConsult);
            binomeManager.saveBinome(infosConsult.getBinome());
            professeurManager.saveProfesseur(infosConsult.getProfesseur());
            return infosConsult;
        }
        else
            return infosConsultRepository.save(infosConsult);
    }

    @Override
    public void deleteInfosConsult(Long id) {
        infosConsultRepository.delete(id);
    }
}
