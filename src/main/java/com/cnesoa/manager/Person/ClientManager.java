package com.cnesoa.manager.Person;

import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Person.Client;

/**
 * Created by Maxime on 30/03/2016.
 */
public interface ClientManager {

    Iterable<Client> listAllClient();

    Client getClientById(Long id);

    Client saveClient(Client client);

    void deleteClient(Long id);

    void removeAnimal(Client client, Animal animal);

}
