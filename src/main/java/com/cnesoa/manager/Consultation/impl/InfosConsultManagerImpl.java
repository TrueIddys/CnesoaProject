package com.cnesoa.manager.Consultation.impl;

import com.cnesoa.domain.Consultation.InfosConsult;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.Consultation.InfosConsultManager;
import com.cnesoa.manager.Person.ProfesseurManager;
import com.cnesoa.repository.consultation.InfosConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 07/04/2016.
 */

@Service
public class InfosConsultManagerImpl implements InfosConsultManager{

    //Autowiring of beans

    @Autowired
    private InfosConsultRepository infosConsultRepository;

    @Autowired
    private BinomeManager binomeManager;

    @Autowired
    private ProfesseurManager professeurManager;

    /*_____________________________________________*/

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
//            infosConsultRepository.save(infosConsult);
//            binomeManager.saveBinome(infosConsult.getBinome());
//            professeurManager.saveProfesseur(infosConsult.getProfesseur());
        }
            return infosConsultRepository.save(infosConsult);
    }

    @Override
    public void deleteInfosConsult(Long id) {
        InfosConsult infosConsult = infosConsultRepository.findOne(id);
        binomeManager.removeInfosConsult(infosConsult.getBinome(), infosConsult);
    }
}
