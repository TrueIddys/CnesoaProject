package com.cnesoa.controleur.person;

import com.cnesoa.domain.Person.Eleve;
import com.cnesoa.manager.Person.Contact.ContactManager;
import com.cnesoa.manager.Person.EleveManager;
import com.cnesoa.utils.CurrentUser;
import com.cnesoa.validator.EleveValidator;
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
 * Created by Maxime on 05/04/2016.
 */
@Controller
public class EleveController {

    //Autowiring of beans

    @Autowired
    private ContactManager contactManager;

    @Autowired
    private EleveManager eleveManager;


    /*___________________________________*/

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("eleve/new")
    public String newEleve(Model model){
        model.addAttribute("eleve", new Eleve());
        return "eleve/eleveform";
    }

    @PreAuthorize("hasAuthority('ROLE_ELEVE')")
    @RequestMapping("eleve/me")
    public String goToEleveMe(Model model, Authentication authentication){
        model.addAttribute("eleve", eleveManager.getEleveById(((CurrentUser)authentication.getPrincipal()).getId()));
        return "eleve/eleveshow";
    }

    @PreAuthorize("@currentUserManagerImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "eleve", method = RequestMethod.POST)
    public String saveEleve(Model model, Eleve eleve, BindingResult result){
        EleveValidator eleveValidator = new EleveValidator();
        eleveValidator.validate(eleve, result);
        if (!contactManager.checkUniqueMail(eleve.getContact().getMail()) && eleve.getContact().getMail() != null
                && eleve.getId() == null){
            model.addAttribute("eleve", eleve);
            model.addAttribute("error", "Cette adresse email est déjà utilisée par un élève.");
            return "eleve/eleveform";
        }
        if (!contactManager.checkUniqueTel(eleve.getContact().getTel()) && eleve.getContact().getTel() != null
                && eleve.getId() == null){
            model.addAttribute("eleve", eleve);
            model.addAttribute("error", "Ce numéro de téléphone est déjà utilisé par un élève.");
            return "eleve/eleveform";
        }
        eleveManager.saveEleve(eleve);
        return "redirect:/eleve/" + eleve.getId();
    }

    @PreAuthorize("@currentUserManagerImpl.canAccessUser(principal, #id)")
    @RequestMapping("eleve/{id}")
    public String showEleve(@PathVariable Long id, Model model){
        model.addAttribute("eleve", eleveManager.getEleveById(id));
        model.addAttribute("heures", eleveManager.numberOfConsultHours(id));
        return "eleve/eleveshow";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/eleves", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("eleves", eleveManager.listAllEleve());
        return "eleve/eleves";
    }

    @PreAuthorize("@currentUserManagerImpl.canAccessUser(principal, #id)")
    @RequestMapping("eleve/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("eleve", eleveManager.getEleveById(id));
        return "eleve/eleveform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("eleve/delete/{id}")
    public String delete(@PathVariable Long id){
        eleveManager.deleteEleve(id);
        return "redirect:/eleves";
    }
}
