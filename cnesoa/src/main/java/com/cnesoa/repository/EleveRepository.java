package com.cnesoa.repository;

import com.cnesoa.domain.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 05/04/2016.
 */
public interface EleveRepository extends JpaRepository<Eleve, Long> {
}
