package com.cnesoa.repository;

import com.cnesoa.domain.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 08/04/2016.
 */
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
