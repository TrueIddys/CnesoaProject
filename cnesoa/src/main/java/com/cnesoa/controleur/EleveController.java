package com.cnesoa.controleur;

import com.cnesoa.domain.Eleve;
import com.cnesoa.manager.EleveManager;
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
public class EleveController {

    private EleveManager eleveManager;

    @Autowired
    public void setEleveManager(EleveManager eleveManager){
        this.eleveManager = eleveManager;
    }

    @RequestMapping("eleve/new")
    public String newEleve(Model model){
        model.addAttribute("eleve", new Eleve());
        return "eleve/eleveform";
    }

    @RequestMapping(value = "eleve", method = RequestMethod.POST)
    public String saveEleve(Eleve eleve){
        eleveManager.saveEleve(eleve);
        return "redirect:/eleve/" + eleve.getId();
    }

    @RequestMapping("eleve/{id}")
    public String showEleve(@PathVariable Long id, Model model){
        model.addAttribute("eleve", eleveManager.getEleveById(id));
        return "eleve/eleveshow";
    }

    @RequestMapping(value = "/eleves", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("eleves", eleveManager.listAllEleve());
        return "eleve/eleves";
    }

    @RequestMapping("eleve/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("eleve", eleveManager.getEleveById(id));
        return "eleve/eleveform";
    }

    @RequestMapping("eleve/delete/{id}")
    public String delete(@PathVariable Long id){
        eleveManager.deleteEleve(id);
        return "redirect:/eleves";
    }
}
