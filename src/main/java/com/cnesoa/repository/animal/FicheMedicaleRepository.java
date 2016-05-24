package com.cnesoa.repository.animal;

import com.cnesoa.domain.Animal.FicheMedicale;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 06/04/2016.
 */
public interface FicheMedicaleRepository extends JpaRepository<FicheMedicale, Long> {
}
