package com.cnesoa.controleur.consultation;

import com.cnesoa.domain.Consultation.Traitement;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.manager.Consultation.ConsultationManager;
import com.cnesoa.manager.Consultation.TraitementManager;
import com.cnesoa.manager.CurrentUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxime on 19/04/2016.
 */

@Controller
public class TraitementController {

    //Autowiring of beans

    @Autowired
    private TraitementManager traitementManager;

    @Autowired
    private ConsultationManager consultationManager;

    @Autowired
    private CurrentUserManager currentUserManager;

    /*_______________________________________________________*/

    @RequestMapping("traitement/{traitementId}")
    public String goToTraitement(@PathVariable Long traitementId, Model model){
        if (currentUserManager.checkTraitement(traitementId) &&
                traitementManager.getTraitementById(traitementId).getConsultation().getDiagnostic().getValide()){
            model.addAttribute("traitement", traitementManager.getTraitementById(traitementId));
            if (traitementManager.getTraitementById(traitementId).getValide() == true)
                model.addAttribute("isEnabled", true);
            else
                model.addAttribute("isEnabled", false);
            return "consultation/traitementform";
        }
        else
            throw new AccessDeniedException("L'utilisateur n'as pas les droits d'accès à ce traitement.");
    }

    @RequestMapping(value = "traitement", method = RequestMethod.POST)
    public String saveTraitement(Traitement traitement){
        if (traitement == null){
            throw new NullObjectException("Le traitement n'as pas pu être sauvegardé.");
        }
        if (currentUserManager.checkTraitement(traitement.getId())){
            if (!traitement.getValide()) {
                traitement.setValide(true);
                traitementManager.saveTraitement(traitement);
            }
            return "redirect:/traitement/" + traitement.getId();
        }
        else
            throw new AccessDeniedException("L'utilisateur n'as pas les droits d'accès à ce traitement.");
    }
}
