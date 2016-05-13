package com.cnesoa.manager.Consultation;

import com.cnesoa.domain.Consultation.Diagnostic;

/**
 * Created by Maxime on 15/04/2016.
 */
public interface DiagnosticManager {

    Iterable<Diagnostic> listAllDiagnostic();

    Diagnostic getDiagnosticById(Long id);

    Diagnostic saveDiagnostic(Diagnostic diagnostic);

    void deleteDiagnostic(Long id);
}
