package com.cnesoa.repository.person;

import com.cnesoa.domain.Person.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 06/04/2016.
 */
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}
