package com.cnesoa.repository.consultation;

import com.cnesoa.domain.Consultation.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maxime on 15/04/2016.
 */
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
}
