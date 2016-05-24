package com.cnesoa.controleur.consultation;

import com.cnesoa.domain.Consultation.InfosConsult;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.manager.Consultation.InfosConsultManager;
import com.cnesoa.manager.CurrentUserManager;
import com.cnesoa.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Maxime on 19/04/2016.
 */

@Controller
public class BilanController {

    //Autowiring of beans

    @Autowired
    private InfosConsultManager infosConsultManager;

    @Autowired
    private CurrentUserManager currentUserManager;

    /*____________________________________________________________*/

    @RequestMapping("bilan/{bilanId}")
    public String goToBilan(@RequestParam(value = "codeProf", required = false) String codeProf, @PathVariable Long bilanId, Model model) {
        if (!infosConsultManager.getInfosConsultById(bilanId).getConsultation().getDiagnostic().getValide() &&
                !infosConsultManager.getInfosConsultById(bilanId).getConsultation().getTraitement().getValide()) {
            throw new AccessDeniedException("Vous ne pouvez pas accéder au bilan" +
                    " sans avoir remplir le diagnostic et traitement.");
        }
        if (currentUserManager.getUser().getRole() != Role.ROLE_ADMIN){
            if (codeProf == null || codeProf.isEmpty()) {
                throw new NullObjectException("Le code n'as pas été renseigné.");
            }
            if (!currentUserManager.checkInfosConsult(bilanId)) {
                throw new AccessDeniedException("Vous n'avez pas les droits d'accès à ce bilan.");
            }
            if (! (new BCryptPasswordEncoder().matches(codeProf,
                    infosConsultManager.getInfosConsultById(bilanId).getProfesseur().getCode()))){
                throw new AccessDeniedException("Le code renseigné n'est pas le bon.");
            }
        }
        model.addAttribute("infosConsult", infosConsultManager.getInfosConsultById(bilanId));
        return "consultation/bilanform";

    }

    @RequestMapping(value = "bilan", method = RequestMethod.POST)
    public String saveBilan(@RequestParam Integer noteDiag,
                            @RequestParam Integer noteTrait,
                            @RequestParam String remarque,
                            InfosConsult infosConsult, Model model){
        if (infosConsult == null){
            throw new NullObjectException("Le bilan n'as pas pu être sauvegardé.");
        }
        if (currentUserManager.checkInfosConsult(infosConsult.getId())){
            infosConsultManager.saveInfosConsult(infosConsult);
            model.addAttribute("infosConsult", infosConsult);
            return "consultation/bilanform";
        }
        else
            throw new AccessDeniedException("L'utilisateur n'as pas les droits d'accès à ce traitement.");
    }
}
