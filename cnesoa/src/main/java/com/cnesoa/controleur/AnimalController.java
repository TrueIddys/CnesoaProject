package com.cnesoa.controleur;

import com.cnesoa.domain.Animal;
import com.cnesoa.manager.AnimalManager;
import com.cnesoa.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("animal", new Animal());
        model.addAttribute("client", clientManager.getClientById(clientId));
        return "animalform";
    }

    @RequestMapping(value = "animal", method = RequestMethod.POST)
    public String goToAnimal(@RequestParam(value="animaux") String animalId){
        return "redirect:/animal/"+animalId;
    }

    @RequestMapping(value = "client/{clientId}/animal", method = RequestMethod.POST)
    public String saveAnimal(Animal animal, @PathVariable Long clientId){
        if (animal.getId() != null)
            animalManager.saveAnimal(animal);
        else
            animalManager.addAnimal(clientId, animal);
        return "redirect:/animal/"+animal.getId();
    }

    @RequestMapping("animal/{id}")
    public String showAnimal(@PathVariable Long id, Model model){
        model.addAttribute("animal", animalManager.getAnimalById(id));
        return "animalshow";
    }

    @RequestMapping(value = "animals", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("animals", animalManager.listAllAnimal());
        return "animals";
    }

    @RequestMapping("animal/{animalId}/edit/{clientId}")
    public String edit(@PathVariable Long animalId, @PathVariable Long clientId, Model model){
        model.addAttribute("animal", animalManager.getAnimalById(animalId));
        model.addAttribute("client", clientManager.getClientById(clientId));

        return "animalform";
    }

    @RequestMapping("animal/delete/{id}")
    public String delete(@PathVariable Long id){
        animalManager.deleteAnimal(id);
        return "redirect:/animals";
    }



}
