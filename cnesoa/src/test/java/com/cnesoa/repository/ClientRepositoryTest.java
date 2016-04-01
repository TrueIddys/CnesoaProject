package com.cnesoa.repository;

import com.cnesoa.RepositoryConfiguration;
import com.cnesoa.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Maxime on 30/03/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ClientRepositoryTest {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Test
    public void shouldSaveProduct()
    {
        //setup product
        Client client = new Client();
        client.setNom("Lapin");
        client.setPrenom("Georges");
        client.setMail("georgesla@cnesoa.fr");

        //save product, verify id has value after save
        assertNull(client.getId());
        clientRepository.save(client);
        assertNotNull(client.getId());

        //fetch from DB
        Client fetched = clientRepository.findOne(client.getId());

        //should not be null
        assertNotNull(fetched);

        //should be equal
        assertEquals(client.getId(), fetched.getId());
        assertEquals(client.getNom(), fetched.getNom());

        //update description and save
        fetched.setNom("Rabbit");
        clientRepository.save(fetched);

        //get from DB, should be updated
        Client fetchedUpdated = clientRepository.findOne(fetched.getId());
        assertEquals(fetched.getNom(),  fetchedUpdated.getNom());

        //verify counts of products in DB
        long productCount = clientRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<Client> products = clientRepository.findAll();

        int count = 0;

        for (Client P : products)
        {
            count++;
        }

        assertEquals(count, 1);


    }


}
