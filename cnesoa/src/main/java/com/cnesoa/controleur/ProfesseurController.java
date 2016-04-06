package com.cnesoa.controleur;

import com.cnesoa.domain.Professeur;
import com.cnesoa.manager.ProfesseurManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxime on 06/04/2016.
 */

@Controller
public class ProfesseurController {

    private ProfesseurManager professeurManager;

    @Autowired
    public void setProfesseurManager(ProfesseurManager professeurManager){
        this.professeurManager = professeurManager;
    }

    @RequestMapping("professeur/new")
    public String newProfesseur(Model model){
        model.addAttribute("professeur", new Professeur());
        return "professeur/professeurform";
    }

    @RequestMapping(value = "professeur", method = RequestMethod.POST)
    public String saveProfesseur(Professeur professeur){
        professeurManager.saveProfesseur(professeur);
        return "redirect:/professeur/" + professeur.getId();
    }

    @RequestMapping("professeur/{id}")
    public String showProfesseur(@PathVariable Long id, Model model){
        model.addAttribute("professeur", professeurManager.getProfesseurById(id));
        return "professeur/professeurshow";
    }

    @RequestMapping(value = "/professeurs", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("professeurs", professeurManager.listAllProfesseur());
        return "professeur/professeurs";
    }

    @RequestMapping("professeur/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("professeur", professeurManager.getProfesseurById(id));
        return "professeur/professeurform";
    }

    @RequestMapping("professeur/delete/{id}")
    public String delete(@PathVariable Long id){
        professeurManager.deleteProfesseur(id);
        return "redirect:/professeurs";
    }
}
