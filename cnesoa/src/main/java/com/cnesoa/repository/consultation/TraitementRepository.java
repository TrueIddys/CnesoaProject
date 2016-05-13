package com.cnesoa.repository.consultation;

import com.cnesoa.domain.Consultation.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 08/04/2016.
 */
public interface TraitementRepository extends JpaRepository<Traitement, Long> {
}
