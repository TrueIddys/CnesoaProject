package com.cnesoa.controleur.person;

import com.cnesoa.domain.Person.Professeur;
import com.cnesoa.manager.Person.Contact.ContactManager;
import com.cnesoa.manager.Person.ProfesseurManager;
import com.cnesoa.utils.CurrentUser;
import com.cnesoa.validator.ProfesseurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxime on 06/04/2016.
 */

@Controller
public class    ProfesseurController {

    //Autowiring of beans

    @Autowired
    private ProfesseurManager professeurManager;

    @Autowired
    private ContactManager contactManager;

    /*_______________________________________________________*/

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("professeur/new")
    public String newProfesseur(Model model){
        model.addAttribute("professeur", new Professeur());
        return "professeur/professeurform";
    }

    @PreAuthorize("hasAuthority('ROLE_PROF')")
    @RequestMapping("/professeur/me")
    public String goToProfesseurMe(Model model, Authentication authentication){
        model.addAttribute("professeur", professeurManager.getProfesseurById(((CurrentUser)authentication.getPrincipal()).getId()));
        return "professeur/professeurshow";
    }

    @PreAuthorize("@currentUserManagerImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "professeur", method = RequestMethod.POST)
    public String saveProfesseur(Model model, Professeur professeur, BindingResult result){
        ProfesseurValidator professeurValidator = new ProfesseurValidator();
        professeurValidator.validate(professeur, result);
        if (!contactManager.checkUniqueMail(professeur.getContact().getMail()) && professeur.getContact().getMail() != null
                && professeur.getId() == null){
            model.addAttribute("professeur", professeur);
            model.addAttribute("error", "Cette adresse email est déjà utilisée par un professeur.");
            return "professeur/professeurform";
        }
        if (!contactManager.checkUniqueTel(professeur.getContact().getTel()) && professeur.getContact().getTel() != null
                && professeur.getId() == null){
            model.addAttribute("professeur", professeur);
            model.addAttribute("error", "Ce numéro de téléphone est déjà utilisé par un professeur.");
            return "professeur/professeurform";
        }
        professeurManager.saveProfesseur(professeur);
        return "redirect:/professeur/" + professeur.getId();
    }

    @PreAuthorize("@currentUserManagerImpl.canAccessUser(principal, #id)")
    @RequestMapping("professeur/{id}")
    public String showProfesseur(@PathVariable Long id, Model model){
        model.addAttribute("professeur", professeurManager.getProfesseurById(id));
        return "professeur/professeurshow";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/professeurs", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("professeurs", professeurManager.listAllProfesseur());
        return "professeur/professeurs";
    }

    @PreAuthorize("@currentUserManagerImpl.canAccessUser(principal, #id)")
    @RequestMapping("professeur/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("professeur", professeurManager.getProfesseurById(id));
        return "professeur/professeurform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("professeur/delete/{id}")
    public String delete(@PathVariable Long id){
        professeurManager.deleteProfesseur(id);
        return "redirect:/professeurs";
    }
}
