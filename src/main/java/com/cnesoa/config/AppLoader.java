//package com.cnesoa.config;
//
//import com.cnesoa.domain.Animal.Animal;
//import com.cnesoa.domain.Binome;
//import com.cnesoa.domain.Consultation.Consultation;
//import com.cnesoa.domain.Person.Client;
//import com.cnesoa.domain.Person.Contact.Adresse;
//import com.cnesoa.domain.Person.Eleve;
//import com.cnesoa.domain.Person.Professeur;
//import com.cnesoa.domain.Person.User;
//import com.cnesoa.manager.Animal.AnimalManager;
//import com.cnesoa.manager.BinomeManager;
//import com.cnesoa.manager.Consultation.ConsultationManager;
//import com.cnesoa.manager.Person.ClientManager;
//import com.cnesoa.manager.Person.Contact.AdresseManager;
//import com.cnesoa.manager.Person.EleveManager;
//import com.cnesoa.manager.Person.ProfesseurManager;
//import com.cnesoa.manager.Person.UserManager;
//import com.cnesoa.utils.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.util.Calendar;
//import java.util.Date;
//
///**
// * Created by Maxime on 30/03/2016.
// * used to load basic user/consultation/animals etc ...
// */
//
//@Component
//public class AppLoader implements ApplicationListener<ContextRefreshedEvent>{
//
//    //autowiring of beans
//
//    @Autowired
//    private ClientManager clientManager;
//
//    @Autowired
//    private AnimalManager animalManager;
//
//    @Autowired
//    private EleveManager eleveManager;
//
//    @Autowired
//    private ProfesseurManager professeurManager;
//
//    @Autowired
//    private ConsultationManager consultationManager;
//
//    @Autowired
//    private BinomeManager binomeManager;
//
//    @Autowired
//    private AdresseManager adresseManager;
//
//    @Autowired
//    private UserManager userManager;
//
//    /*______________________*/
//
//
//    public Client clientBuilder(String nom, String prenom, String email, String tel){
//        Client client = new Client(nom, prenom, email, tel);
//        return clientManager.saveClient(client);
//    }
//
//    public Animal animalBuilder(String nom, String race, String type, String sexe, String robe, Client client){
//        Animal animal = new Animal(nom, race, sexe);
//        animal.setRobe(robe);
//        animal.setType(type);
//        animal.setClient(client);
//        return animalManager.saveAnimal(animal);
//    }
//
//    public Adresse adresseBuilder(String numero, String rue, String code, String ville, Client client){
//        Adresse adresse = new Adresse(numero, rue, code, ville);
//        adresse.setClient(client);
//        return adresseManager.saveAdresse(adresse);
//    }
//
//    public Eleve eleveBuilder(String nom, String prenom, String mail, String tel){
//        Eleve eleve = new Eleve(nom, prenom, mail, tel);
//        eleve.setRole(Role.ROLE_ELEVE);
//        return eleveManager.saveEleve(eleve);
//    }
//
//    public Professeur professeurBuilder(String nom, String prenom, String mail, String tel){
//        Professeur professeur = new Professeur(nom, prenom, mail, tel);
//        professeur.setRole(Role.ROLE_PROF);
//        return professeurManager.saveProfesseur(professeur);
//    }
//
//    public Binome binomeBuilder(Eleve eleve1, Eleve eleve2, Integer numBinome){
//        Binome binome = new Binome();
//        binome.setEleve1(eleve1);
//        binome.setEleve2(eleve2);
//        binome.setNumBinome(numBinome);
//        return binomeManager.saveBinome(binome);
//    }
//
//    public Consultation consultationBuilder(Calendar c, Animal animal, Binome bin, Professeur prof){
//        Consultation cons = new Consultation();
//        cons.setDateConsultation(c.getTime());
//        cons.setAnimal(animal);
//        cons.getInfosConsult().setBinome(bin);
//        cons.getInfosConsult().setProfesseur(prof);
//        return consultationManager.saveConsultation(cons);
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        Client client1 = clientBuilder("Bouchard", "Gerard", "gerard.dupont@gmail.com", "0645788956");
//        Client client2 = clientBuilder("Lapeyre", "Pierre", "pierre63@gmail.com", "0475234589");
//        Client client3 = clientBuilder("Chesne", "Axel", "tartinedumonde@gmail.com", "0485234589");
//        Client client4 = clientBuilder("Raymond", "Gerard", "flemmard63@gmail.com", "0475244589");
//
//        Adresse adr1 = adresseBuilder("11 bis", "Avenue des Etats-Unis", "63000", "Clermont-Ferrand", client1);
//        Adresse adr2 = adresseBuilder("23", "Rue des Acacias", "63500", "Riom", client2);
//        Adresse adr3 = adresseBuilder("2", "Rue des Brelans", "43700", "Brives", client3);
//        Adresse adr4 = adresseBuilder("105", "Boulevard Lafayette", "69000", "Lyon", client4);
//
//        Animal animal1 = animalBuilder("Rex", "Chien", "Berger allemand", "Male", "Noir", client1);
//        Animal animal2 = animalBuilder("Gerard", "Chat", "Gouttiere", "Femelle", "Blanche", client3);
//        Animal animal3 = animalBuilder("Rookie", "Cheval", "Truc", "Jument", "Marron", client2);
//        Animal animal4 = animalBuilder("Edgar", "Lapin", "Normal","Male", "Gris", client4);
//
//        Eleve eleve1 = eleveBuilder("Peyral", "Maxime", "maxime.peyral@gmail.com", "0667367526");
//        Eleve eleve2 = eleveBuilder("Cortial", "Cedric", "cedric.cortial@gmail.com", "0654785698");
//        Eleve eleve3= eleveBuilder("Morel", "Clement", "clement.morel@gmail.com", "0652365698");
//        Eleve eleve4= eleveBuilder("Bertola", "Gerard", "leduc@gmail.com", "0682365698");
//        Eleve eleve5= eleveBuilder("Lafond", "Claire", "koaladu42@gmail.com", "0652865698");
//        Eleve eleve6= eleveBuilder("Pyetrika", "Stanilas", "lerusseponot@hotmail.fr", "0654885698");
//
//        Professeur prof1 = professeurBuilder("Mazuel", "Adrien", "lebogossdu43@gmail.com", "0658134679");
//        Professeur prof2 = professeurBuilder("Robinet", "Gerard", "rockeur63@gmail.com", "0658138679");
//        Professeur prof3 = professeurBuilder("Jaboin", "Quentin", "catocathé@outlook.com", "0654346494");
//
//        Binome bin1 = binomeBuilder(eleve1, eleve2, 1);
//        Binome bin2 = binomeBuilder(eleve3, eleve4, 2);
//        Binome bin3 = binomeBuilder(eleve5, eleve6, 3);
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(Date.from(Instant.now()));
//        c.add(Calendar.DATE, 1);
//        c.set(Calendar.HOUR_OF_DAY, 8);
//        c.set(Calendar.MINUTE, 30);
//
//        Consultation cons1 = consultationBuilder(c, animal1, bin1, prof1);
//        Consultation cons2 = consultationBuilder(c, animal2, bin2, prof2);
//        Consultation cons3 = consultationBuilder(c, animal3, bin3, prof3);
//        consultationBuilder(c, animal2, bin3, prof1);
//        consultationBuilder(c, animal2, bin3, prof1);
//        consultationBuilder(c, animal2, bin3, prof1);
//        consultationBuilder(c, animal2, bin3, prof1);
//
//        c.add(Calendar.MINUTE, 90);
//
//        Consultation cons4 = consultationBuilder(c, animal4, bin1, prof2);
//        Consultation cons5 = consultationBuilder(c, animal1, bin2, prof3);
//        Consultation cons6 = consultationBuilder(c, animal3, bin3, prof1);
//
//        c.add(Calendar.MINUTE, 180);
//
//        Consultation cons7 = consultationBuilder(c, animal2, bin1, prof3);
//        Consultation cons8 = consultationBuilder(c, animal1, bin3, prof2);
//        Consultation cons9 = consultationBuilder(c, animal4, bin2, prof1);
//
//        c.add(Calendar.DATE, -36);
//
//        Consultation cons10 = consultationBuilder(c, animal2, bin1, prof2);
//        cons10.setMotif("Visite : bilan, pleure parfois quand prise sous le ventre");
//        cons10.getDiagnostic().setDouleur("sphère viscérale");
//        cons10.getDiagnostic().setDynamique("rachis en 2 partie à L3/L4");
//        cons10.getDiagnostic().setPalpatoire("intestins, diaphragme");
//        cons10.getDiagnostic().setDiagnosticText("intestins = GI (+ système digestif global), diaphragme, foie");
//        cons10.getDiagnostic().setValide(true);
//        cons10.getTraitement().setDetails("Crane sacré : rééquilibrage NRP /n" +
//                "TOG : rachis, sternum /n" +
//                "Viscéral : intestion (GI+++) + sphère digestive/n" +
//                "Tumilaire : diaphragme (+ mécanique) /n" +
//                "Myotensif : rachis (avec antérieur et postérieur) /n" +
//                "Massages + palpe roulé") ;
//        cons10.getTraitement().setResultat("nécessite autre visite");
//        cons10.getTraitement().setConseils("continuer les massages et le palpe roulé, éviter de la pate " +
//                "en le prenant sous le ventre pendant 24-48h, prochaine visite dans 1 Mois");
//        cons10.getTraitement().setValide(true);
//        cons10.getInfosConsult().setNoteDiag(4);
//        cons10.getInfosConsult().setNoteTrait(3);
//        cons10 = consultationManager.saveConsultation(cons3);
//
//        c.add(Calendar.DATE, 25);
//
//        Consultation cons11 = consultationBuilder(c, animal2, bin1, prof1);
//        cons11.setMotif("Visite de suivi");
//        cons11.getDiagnostic().setDouleur("reins, sphère viscérale");
//        cons11.getDiagnostic().setDynamique("bassin décalé à droite," +
//                "bloc lambaire immobile");
//        cons11.getDiagnostic().setPalpatoire("reins gauche +++, bloc lombaire, tensons des psoas, rein droit");
//        cons11.getDiagnostic().setDiagnosticText("reins tractent l'ensemble de la sphère digestive vers dorsal," +
//                "rein gauche et glande surrénale gauche plus dense," +
//                "psoas spasmés");
//        cons11.getDiagnostic().setValide(true);
//        cons11.getTraitement().setDetails("viscéral : reins (gche+++) et glandes surrehales," +
//                "stretching des muscles psoas," +
//                "réharmonisation de l'axe cranio-sacré") ;
//        cons11.getTraitement().setResultat("nécessite autre visite");
//        cons11.getTraitement().setConseils("continuer les massages et le palper rouler," +
//                "eviter de la porter sous le ventre," +
//                "si possible, faire boire caline pour drainer les reins," +
//                "autre visite pour continuer le travail sur les reins (septembre)");
//        cons11.getTraitement().setValide(true);
//        cons11.getInfosConsult().setNoteDiag(5);
//        cons11.getInfosConsult().setNoteTrait(4);
//        cons11 = consultationManager.saveConsultation(cons4);
//
//        User user1 = new User();
//        user1.getContact().setPrenom("ad");
//        user1.getContact().setNom("min");
//        user1.setRole(Role.ROLE_ADMIN);
//        userManager.saveUser(user1);
//
//    }
//}
