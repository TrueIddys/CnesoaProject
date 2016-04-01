package com.cnesoa.manager;

import com.cnesoa.domain.Animal;
import com.cnesoa.domain.Client;

/**
 * Created by Maxime on 30/03/2016.
 */
public interface ClientManager {

    Iterable<Client> listAllClient();

    Client getClientById(Long id);

    Client saveClient(Client client);

    void deleteClient(Long id);

    Animal addAnimal(Long clientId, Long animalId);
}
