package com.cnesoa.controleur.animal;

import com.cnesoa.domain.Animal.FicheMedicale;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.manager.Animal.FicheMedicaleManager;
import com.cnesoa.manager.CurrentUserManager;
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
public class FicheMedicaleController {

    //Autowiring of beans

    @Autowired
    private FicheMedicaleManager ficheMedicaleManager;

    @Autowired
    private CurrentUserManager currentUserManager;

   /*__________________________________________*/

    @RequestMapping("fichemed/{ficheMedicaleId}")
    public String goToFicheMed(@PathVariable Long ficheMedicaleId, Model model){
        if (currentUserManager.checkAnimal(ficheMedicaleManager.getFicheMedicaleById(ficheMedicaleId).getAnimal().getId())) {
            model.addAttribute("ficheMedicale", ficheMedicaleManager.getFicheMedicaleById(ficheMedicaleId));
            return "animal/fichemedicaleform";
        }
        else
            throw new AccessDeniedException("Cet utilisateur n'as pas le droit d'accéder à la fiche médicale de cet animal");

    }

    @RequestMapping(value = "fichemed", method = RequestMethod.POST)
    public String saveFicheMed(FicheMedicale ficheMedicale){
        if (ficheMedicale == null){
            throw new NullObjectException("La fiche médicale n'as pas pu être enregistrée.");
        }
        if (currentUserManager.checkAnimal(ficheMedicale.getAnimal().getId())) {
            ficheMedicaleManager.saveFicheMedicale(ficheMedicale);
            return "redirect:/fichemed/" + ficheMedicale.getId();
        }
        else
            throw new AccessDeniedException("Cet utilisateur n'as pas le droit d'accéder à la fiche médicale de cet animal");

    }
}
