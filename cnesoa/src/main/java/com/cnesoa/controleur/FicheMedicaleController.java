package com.cnesoa.controleur;

import com.cnesoa.domain.FicheMedicale;
import com.cnesoa.manager.FicheMedicaleManager;
import org.springframework.beans.factory.annotation.Autowired;
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

    private FicheMedicaleManager ficheMedicaleManager;


    @Autowired
    public void setFicheMedicaleManager(FicheMedicaleManager ficheMedicaleManager){
        this.ficheMedicaleManager = ficheMedicaleManager;
    }

    @RequestMapping("fichemed/{ficheMedicaleId}")
    public String goToFicheMed(@PathVariable Long ficheMedicaleId, Model model){
        model.addAttribute("ficheMedicale", ficheMedicaleManager.getFicheMedicaleById(ficheMedicaleId));
        return "animal/fichemedicaleform";
    }

    @RequestMapping(value = "fichemed", method = RequestMethod.POST)
    public String saveFicheMed(FicheMedicale ficheMedicale){
        ficheMedicaleManager.saveFicheMedicale(ficheMedicale);
        return "redirect:/fichemed/" + ficheMedicale.getId();
    }
}
