package com.cnesoa.controleur;

import com.cnesoa.config.DateEditor;
import com.cnesoa.domain.Animal;
import com.cnesoa.manager.AnimalManager;
import com.cnesoa.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 */

@Controller
public class AnimalController {

    private AnimalManager animalManager;
    private ClientManager clientManager;

    @Autowired
    public void setAnimalManager(AnimalManager animalManager){
        this.animalManager = animalManager;
    }

    @Autowired
    public void setClientManager(ClientManager clientManager){
        this.clientManager = clientManager;
    }

    @RequestMapping("client/{clientId}/animal/new")
    public String newAnimal(@PathVariable Long clientId, Model model){
        Animal animal = new Animal();
        animal.setClient(clientManager.getClientById(clientId));
        model.addAttribute("animal", animal);
        return "animal/animalform";
    }

    @RequestMapping(value = "animal", method = RequestMethod.POST)
    public String goToAnimal(@RequestParam(value="animaux") String animalId){
        return "redirect:/animal/"+animalId;
    }

    @RequestMapping(value = "client/animal", method = RequestMethod.POST)
    public String saveAnimal(Animal animal){
        animalManager.saveAnimal(animal);
        return "redirect:/animal/"+animal.getId();
    }

    @RequestMapping("animal/{id}")
    public String showAnimal(@PathVariable Long id, Model model){
        model.addAttribute("animal", animalManager.getAnimalById(id));
        return "animal/animalshow";
    }

    @RequestMapping(value = "animals", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("animals", animalManager.listAllAnimal());
        return "animal/animals";
    }

    @RequestMapping("animal/{animalId}/edit")
    public String edit(@PathVariable Long animalId, Model model){
        model.addAttribute("animal", animalManager.getAnimalById(animalId));

        return "animal/animalform";
    }

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
