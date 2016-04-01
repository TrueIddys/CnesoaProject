package com.cnesoa.repository;

import com.cnesoa.domain.Cotisation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 30/03/2016.
 */
public interface CotisationRepository extends JpaRepository<Cotisation, Long>{
}
