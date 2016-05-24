package com.cnesoa.manager.Consultation.impl;

import com.cnesoa.domain.Consultation.Diagnostic;
import com.cnesoa.manager.Consultation.DiagnosticManager;
import com.cnesoa.repository.consultation.DiagnosticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 15/04/2016.
 */

@Service
public class DiagnosticManagerImpl implements DiagnosticManager {

    //Autowiring of beans

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    /*______________________________________________*/

    @Override
    public Iterable<Diagnostic> listAllDiagnostic() {
        return diagnosticRepository.findAll();
    }

    @Override
    public Diagnostic getDiagnosticById(Long id) {
        return diagnosticRepository.findOne(id);
    }

    @Override
    public Diagnostic saveDiagnostic(Diagnostic diagnostic) {
        return diagnosticRepository.save(diagnostic);
    }

    @Override
    public void deleteDiagnostic(Long id) {
        diagnosticRepository.delete(id);
    }
}
