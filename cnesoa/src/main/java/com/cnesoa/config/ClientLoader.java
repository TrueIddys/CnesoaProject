package com.cnesoa.config;

import com.cnesoa.domain.Animal;
import com.cnesoa.domain.Client;
import com.cnesoa.manager.AnimalManager;
import com.cnesoa.manager.ClientManager;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Maxime on 30/03/2016.
 */

@Component
public class ClientLoader implements ApplicationListener<ContextRefreshedEvent>{

    private ClientManager clientManager;

    private AnimalManager animalManager;

    private Logger log = Logger.getLogger(ClientLoader.class);

    @Autowired
    public void setClientManager(ClientManager clientManager){
        this.clientManager = clientManager;
    }

    @Autowired
    public void setAnimalManager(AnimalManager animalManager){
        this.animalManager = animalManager;
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
        client1.addAnimal(animal1);
        clientManager.saveClient(client1);



        log.info("Saved client - id "+client1.getId());

        Client client2 = new Client();
        client2.setNom("Lapeyre");
        client2.setPrenom("Pierre");
        client2.setMail("pierre63@gmail.com");
        client2.setRue("2 impasse des Chauves");
        client2.setVille("Riom");
        clientManager.saveClient(client2);

        log.info("Saved client - id "+client2.getId());




    }
}
