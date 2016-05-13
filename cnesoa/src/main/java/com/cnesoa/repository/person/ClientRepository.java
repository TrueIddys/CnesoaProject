package com.cnesoa.repository.person;

import com.cnesoa.domain.Person.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 30/03/2016.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}
