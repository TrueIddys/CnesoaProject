package com.cnesoa.controleur.consultation;

import com.cnesoa.domain.Consultation.Diagnostic;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.manager.Consultation.ConsultationManager;
import com.cnesoa.manager.Consultation.DiagnosticManager;
import com.cnesoa.manager.CurrentUserManager;
import com.cnesoa.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxime on 05/04/2016.
 */

@Controller
public class DiagnosticController {

    //Autowiring of beans

    @Autowired
    private DiagnosticManager diagnosticManager;

    @Autowired
    private ConsultationManager consultationManager;

    @Autowired
    private CurrentUserManager currentUserManager;

    /*_______________________________________________*/

    @RequestMapping("diagnostic/{diagnosticId}")
    public String goToDiagnostic(@PathVariable Long diagnosticId, Model model){
        if (currentUserManager.checkDiagnostic(diagnosticId)){
            model.addAttribute("diagnostic", diagnosticManager.getDiagnosticById(diagnosticId));
            if (diagnosticManager.getDiagnosticById(diagnosticId).getValide() == true &&
                    currentUserManager.getUser().getRole() == Role.ROLE_ADMIN)
                model.addAttribute("isEnabled", true);
            else
                model.addAttribute("isEnabled", false);
            return "consultation/diagnosticform";
        }
        else
            throw new AccessDeniedException("L'utilisateur n'as pas les droits d'accès à ce diagnostic.");
    }

    @RequestMapping(value = "diagnostic", method = RequestMethod.POST)
    public String saveDiagnostic(Model model, Diagnostic diagnostic){
        if (diagnostic == null){
            throw new NullObjectException("Le diagnostic n'as pas pu être sauvegardé.");
        }
        if (currentUserManager.checkDiagnostic(diagnostic.getId())){
            if (!diagnostic.getValide()) {
                diagnostic.setValide(true);
                diagnosticManager.saveDiagnostic(diagnostic);
            }
            return "redirect:/traitement/" + diagnostic.getConsultation().getTraitement().getId();
        }
        else
            throw new AccessDeniedException("L'utilisateur n'as pas les droits d'accès à ce diagnostic.");
    }
}
