package com.cnesoa.controleur.animal;

import com.cnesoa.config.DateEditor;
import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.manager.Animal.AnimalManager;
import com.cnesoa.manager.CurrentUserManager;
import com.cnesoa.manager.Person.ClientManager;
import com.cnesoa.validator.AnimalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxime on 30/03/2016.
 */

@Controller
public class AnimalController {

    //Autowiring of beans

    @Autowired
    private AnimalManager animalManager;
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private CurrentUserManager currentUserManager;

    /*________________________________*/

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("client/{clientId}/animal/new")
    public String newAnimal(@PathVariable Long clientId, Model model){
        Animal animal = new Animal();
        animal.setClient(clientManager.getClientById(clientId));
        model.addAttribute("animal", animal);
        return "animal/animalform";
    }


    @RequestMapping(value = "animal", method = RequestMethod.POST)
    public String goToAnimal(@RequestParam(value="animaux") String animalId){
        if (currentUserManager.checkAnimal(Long.parseLong(animalId)))
            return "redirect:/animal/"+animalId;
        else
            throw new AccessDeniedException("Cet utilisateur n'as pas le droit d'accéder à la page de cet animal");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "client/animal", method = RequestMethod.POST)
    public String saveAnimal(Animal animal, BindingResult result){
        AnimalValidator animalValidator = new AnimalValidator();
        animalValidator.validate(animal, result);
        animal = animalManager.saveAnimal(animal);
        return "redirect:/animal/"+animal.getId();
    }

    @RequestMapping("animal/{animalId}/lastconsult")
    public String lastConsult(@PathVariable Long animalId, Model model){
            List<Consultation> listConsult = animalManager.getAnimalById(animalId).getConsultations();
        if (!listConsult.isEmpty()){
            Collections.sort(listConsult);
            return "redirect:/consultation/"+listConsult.get(listConsult.size()-1).getId();
        }
        else {
            throw new NullObjectException("Il n'y a pas de consultations.");
        }

    }

    @RequestMapping("animal/{id}")
    public String showAnimal(@PathVariable Long id, Model model){
        if (currentUserManager.checkAnimal(id)) {
            model.addAttribute("animal", animalManager.getAnimalById(id));
            return "animal/animalshow";
        }
        else
            throw new AccessDeniedException("Cet utilisateur n'as pas le droit d'accéder à la page de cet animal");

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "animals", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("animals", animalManager.listAllAnimal());
        return "animal/animals";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("animal/{animalId}/edit")
    public String edit(@PathVariable Long animalId, Model model){
        model.addAttribute("animal", animalManager.getAnimalById(animalId));

        return "animal/animalform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("animal/delete/{id}")
    public String delete(@PathVariable Long id){
        Long clientId = animalManager.getAnimalById(id).getClient().getId();
        animalManager.deleteAnimal(id);
        return "redirect:/client/" + clientId;
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

}
