package com.cnesoa.manager.impl;

import com.cnesoa.domain.InfosConsult;
import com.cnesoa.manager.InfosConsultManager;
import com.cnesoa.repository.InfosConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 07/04/2016.
 */

@Service
public class InfosConsultManagerImpl implements InfosConsultManager{

    private InfosConsultRepository infosConsultRepository;

    private EleveManagerImpl eleveManager;

    private ProfesseurManagerImpl professeurManager;

    @Autowired
    private void setProfesseurManager(ProfesseurManagerImpl professeurManager){
        this.professeurManager = professeurManager;
    }

    @Autowired
    private void setEleveManager(EleveManagerImpl eleveManager){
        this.eleveManager = eleveManager;
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
            infosConsult.getEleve1().addInfosConsult(infosConsult);
            infosConsult.getEleve2().addInfosConsult(infosConsult);
            infosConsult.getProfesseur().addInfosConsult(infosConsult);
            infosConsultRepository.save(infosConsult);
            eleveManager.saveEleve(infosConsult.getEleve1());
            eleveManager.saveEleve(infosConsult.getEleve2());
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
