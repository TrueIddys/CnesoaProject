package com.cnesoa.config;

import com.cnesoa.domain.*;
import com.cnesoa.manager.*;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 */

@Component
public class ClientLoader implements ApplicationListener<ContextRefreshedEvent>{

    private ClientManager clientManager;

    private AnimalManager animalManager;

    private EleveManager eleveManager;

    private ProfesseurManager professeurManager;

    private ConsultationManager consultationManager;

    private BinomeManager binomeManager;

    private Logger log = Logger.getLogger(ClientLoader.class);

    @Autowired
    public void setClientManager(ClientManager clientManager){
        this.clientManager = clientManager;
    }

    @Autowired
    public void setAnimalManager(AnimalManager animalManager){
        this.animalManager = animalManager;
    }

    @Autowired
    public void setEleveManager(EleveManager eleveManager){
        this.eleveManager = eleveManager;
    }

    @Autowired
    public void setProfesseurManager(ProfesseurManager professeurManager){
        this.professeurManager = professeurManager;
    }

    @Autowired
    public void setBinomeManager(BinomeManager binomeManager){
        this.binomeManager = binomeManager;
    }

    @Autowired
    public void setConsultationManager(ConsultationManager consultationManager){
        this.consultationManager = consultationManager;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Client client1 = new Client();

        client1.setNom("Dupont");
        client1.setPrenom("Gerard");
        client1.setMail("gerard.dupont@gmail.com");
        client1.setRue("11 bis rue Cote Blatin");
        client1.setVille("Clermont-Ferrand");
        clientManager.saveClient(client1);


        Animal animal1 = new Animal();
        animal1.setNom("Rex");
        animal1.setRace("Chien");
        animal1.setRobe("Noir");
        animal1.setType("Berger allemand");
        animal1.setSexe("Male");
        animal1.setClient(client1);

        animalManager.saveAnimal(animal1);



        log.info("Saved client - id "+client1.getId());

        Client client2 = new Client();
        client2.setNom("Lapeyre");
        client2.setPrenom("Pierre");
        client2.setMail("pierre63@gmail.com");
        client2.setRue("2 impasse des Chauves");
        client2.setVille("Riom");
        clientManager.saveClient(client2);

        log.info("Saved client - id "+client2.getId());

        Eleve eleve1 = new Eleve();
        eleve1.setMail("maxime.peyral@gmail.com");
        eleve1.setNom("Peyral");
        eleve1.setPrenom("Maxime");
        eleve1.setTel("0667367526");
        eleveManager.saveEleve(eleve1);

        Eleve eleve2 = new Eleve();
        eleve2.setMail("cedric.cortial@gmail.com");
        eleve2.setNom("Cortial");
        eleve2.setPrenom("Cedric");
        eleve2.setTel("0667367526");
        eleveManager.saveEleve(eleve2);

        Professeur prof1 = new Professeur();
        prof1.setPrenom("Adrien");
        prof1.setNom("Mazuel");
        prof1.setMail("lachiennedu63@gmail.com");
        prof1.setTel("0689457896");
        professeurManager.saveProfesseur(prof1);

        Binome bin1 = new Binome();
        bin1.setEleve1(eleve1);
        bin1.setEleve2(eleve2);
        bin1.setNumBinome(1);
        binomeManager.saveBinome(bin1);

        Consultation cons1 = new Consultation(animal1);
        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(Instant.now()));
        c.add(Calendar.DATE, 1);
        cons1.setDateConsultation(c.getTime());
        cons1.setInfosConsult(new InfosConsult(cons1));
        cons1.setAnimal(animal1);
        cons1.getInfosConsult().setBinome(bin1);
        cons1.getInfosConsult().setProfesseur(prof1);
        consultationManager.saveConsultation(cons1);

    }
}
