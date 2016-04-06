package com.cnesoa.repository;

import com.cnesoa.domain.FicheMedicale;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 06/04/2016.
 */
public interface FicheMedicaleRepository extends JpaRepository<FicheMedicale, Long> {
}
