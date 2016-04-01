package com.cnesoa;

import com.cnesoa.domain.Animal;
import com.cnesoa.domain.Client;
import com.cnesoa.repository.AnimalRepository;
import com.cnesoa.repository.ClientRepository;
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

    private ClientRepository clientRepository;

    private AnimalRepository animalRepository;

    private Logger log = Logger.getLogger(ClientLoader.class);

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Client client1 = new Client();

        client1.setNom("Dupont");
        client1.setPrenom("Gerard");
        client1.setMail("gerard.dupont@gmail.com");
        client1.setRue("11 bis rue Cote Blatin");
        client1.setVille("Clermont-Ferrand");
        clientRepository.save(client1);


        Animal animal1 = new Animal();
        animal1.setNom("Rex");
        animal1.setRace("Chien");
        animal1.setRobe("Noir");
        animal1.setType("Berger allemand");
        animal1.setSexe("Male");
        animal1.setClient(client1);

        animalRepository.save(animal1);
        client1.addAnimal(animal1);
        clientRepository.save(client1);



        log.info("Saved client - id "+client1.getId());

        Client client2 = new Client();
        client2.setNom("Lapeyre");
        client2.setPrenom("Pierre");
        client2.setMail("pierre63@gmail.com");
        client2.setRue("2 impasse des Chauves");
        client2.setVille("Riom");
        clientRepository.save(client2);

        log.info("Saved client - id "+client2.getId());




    }
}
