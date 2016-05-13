package com.cnesoa.config;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Person.Client;
import com.cnesoa.domain.Person.Contact.Adresse;
import com.cnesoa.domain.Person.Eleve;
import com.cnesoa.domain.Person.Professeur;
import com.cnesoa.domain.Person.User;
import com.cnesoa.manager.Animal.AnimalManager;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.Consultation.ConsultationManager;
import com.cnesoa.manager.Person.ClientManager;
import com.cnesoa.manager.Person.Contact.AdresseManager;
import com.cnesoa.manager.Person.EleveManager;
import com.cnesoa.manager.Person.ProfesseurManager;
import com.cnesoa.manager.Person.UserManager;
import com.cnesoa.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 * used to load basic user/consultation/animals etc ...
 */

@Component
public class AppLoader implements ApplicationListener<ContextRefreshedEvent>{

    //autowiring of beans

    @Autowired
    private ClientManager clientManager;

    @Autowired
    private AnimalManager animalManager;

    @Autowired
    private EleveManager eleveManager;

    @Autowired
    private ProfesseurManager professeurManager;

    @Autowired
    private ConsultationManager consultationManager;

    @Autowired
    private BinomeManager binomeManager;

    @Autowired
    private AdresseManager adresseManager;

    @Autowired
    private UserManager userManager;

    /*______________________*/

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Client client1 = new Client("Bouchard", "Gerard", "gerard.dupont@gmail.com", "0645788956");
        client1 = clientManager.saveClient(client1);

        Animal animal1 = new Animal("Rex", "Chien", "Male");
        animal1.setRobe("Noir");
        animal1.setType("Berger allemand");
        animal1.setClient(client1);
        animal1 = animalManager.saveAnimal(animal1);

        Client client2 = new Client("Lapeyre", "Pierre", "pierre63@gmail.com", "0475234589");
        client2 = clientManager.saveClient(client2);

        Client client3 = new Client("Chesne", "Axel", "tartinedumonde@gmail.com", "0485234589");
        client3 = clientManager.saveClient(client3);

        Animal animal2 = new Animal("Gerard", "Chat", "Femelle");
        animal2.setRobe("Blanche");
        animal2.setType("Gouttiere");
        animal2.setClient(client3);
        animal2 = animalManager.saveAnimal(animal2);

        Client client4 = new Client("Raymond", "Gerard", "flemmard63@gmail.com", "0475244589");
        client4 = clientManager.saveClient(client4);

        Adresse adr1 = new Adresse("11 bis", "Avenue des Etats-Unis", "63000", "Clermont-Ferrand");
        adr1.setClient(client1);
        Adresse adr2 = new Adresse("23", "Rue des Acacias", "63500", "Riom");
        adr2.setClient(client2);
        Adresse adr3 = new Adresse("2", "Rue des Brelans", "43700", "Brives");
        adr3.setClient(client3);
        Adresse adr4 = new Adresse("105", "Boulevard Lafayette", "690000", "Lyon");
        adr4.setClient(client4);
        adresseManager.saveAdresse(adr1);
        adresseManager.saveAdresse(adr2);
        adresseManager.saveAdresse(adr3);
        adresseManager.saveAdresse(adr4);

        Eleve eleve1 = new Eleve("Peyral", "Maxime", "maxime.peyral@gmail.com", "0667367526");
        eleve1.setRole(Role.ROLE_ELEVE);
        eleve1 = eleveManager.saveEleve(eleve1);

        Eleve eleve2 = new Eleve("Cortial", "Cedric", "cedric.cortial@gmail.com", "0654785698");
        eleve2.setRole(Role.ROLE_ELEVE);
        eleve2 = eleveManager.saveEleve(eleve2);

        Eleve eleve3= new Eleve("Morel", "Clement", "clement.morel@gmail.com", "0652365698");
        eleve3.setRole(Role.ROLE_ELEVE);
        eleve3 = eleveManager.saveEleve(eleve3);

        Eleve eleve4= new Eleve("Bertola", "Gerard", "leduc@gmail.com", "0682365698");
        eleve4.setRole(Role.ROLE_ELEVE);
        eleve4 = eleveManager.saveEleve(eleve4);

        Eleve eleve5= new Eleve("Lafond", "Claire", "koaladu42@gmail.com", "0652865698");
        eleve5.setRole(Role.ROLE_ELEVE);
        eleve5 = eleveManager.saveEleve(eleve5);

        Professeur prof1 = new Professeur("Mazuel", "Adrien", "lebogossdu43@gmail.com", "0658134679");
        prof1.setRole(Role.ROLE_PROF);
        prof1 = professeurManager.saveProfesseur(prof1);

        Professeur prof2 = new Professeur("Robinet", "Gerard", "rockeur63@gmail.com", "0658138679");
        prof2.setRole(Role.ROLE_PROF);
        prof2 = professeurManager.saveProfesseur(prof2);

        Binome bin1 = new Binome();
        bin1.setEleve1(eleve1);
        bin1.setEleve2(eleve2);
        bin1.setNumBinome(1);
        bin1 = binomeManager.saveBinome(bin1);

        Binome bin2 = new Binome();
        bin2.setEleve2(eleve4);
        bin2.setEleve1(eleve5);
        bin2.setNumBinome(2);
        bin2 = binomeManager.saveBinome(bin2);

        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(Instant.now()));
        c.add(Calendar.DATE, 1);

        Consultation cons1 = new Consultation();
        cons1.setDateConsultation(c.getTime());
        cons1.setAnimal(animal1);
        cons1.getInfosConsult().setBinome(bin1);
        cons1.getInfosConsult().setProfesseur(prof1);
        cons1 = consultationManager.saveConsultation(cons1);

        c.add(Calendar.DATE, 2);
        c.add(Calendar.HOUR, 1);

        Consultation cons2 = new Consultation();
        cons2.setDateConsultation(c.getTime());
        cons2.setAnimal(animal2);
        cons2.getInfosConsult().setBinome(bin2);
        cons2.getInfosConsult().setProfesseur(prof2);
        cons2 = consultationManager.saveConsultation(cons2);

        User user1 = new User();
        user1.getContact().setPrenom("ad");
        user1.getContact().setNom("min");
        user1.setRole(Role.ROLE_ADMIN);
        userManager.saveUser(user1);

    }
}
