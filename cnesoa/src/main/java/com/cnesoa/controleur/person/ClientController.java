package com.cnesoa.controleur.person;

import com.cnesoa.domain.Person.Client;
import com.cnesoa.manager.Person.ClientManager;
import com.cnesoa.manager.Person.Contact.ContactManager;
import com.cnesoa.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxime on 30/03/2016.
 */

@Controller
public class ClientController {

    //Autowiring of beans

    @Autowired
    private ClientManager clientManager;

    @Autowired
    private ContactManager contactManager;

    /*_________________________________________*/

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("client/new")
    public String newClient(Model model){
        model.addAttribute("client", new Client());
        return "client/clientform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "client", method = RequestMethod.POST)
    public String saveClient(Model model, Client client, BindingResult result){
        ClientValidator clientValidator = new ClientValidator();
        clientValidator.validate(client, result);
        if (!contactManager.checkUniqueMail(client.getContact().getMail()) && client.getContact().getMail() != null
                && client.getId() == null){
            model.addAttribute("client", client);
            model.addAttribute("error", "Cette adresse email est déjà utilisée par un client.");
            return "client/clientform";
        }
        if (!contactManager.checkUniqueTel(client.getContact().getTel()) && client.getContact().getTel() != null
                && client.getId() == null){
            model.addAttribute("client", client);
            model.addAttribute("error", "Ce numéro de téléphone est déjà utilisé par un client.");
            return "client/clientform";
        }
        clientManager.saveClient(client);
        return "redirect:/client/" + client.getId();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("client/{id}")
    public String showClient(@PathVariable Long id, Model model){
        model.addAttribute("client", clientManager.getClientById(id));
        if (clientManager.getClientById(id).getDebutCotisation() != null)
            model.addAttribute("isEnabled", true);
        else
            model.addAttribute("isEnabled", false);
        return "client/clientshow";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("clients", clientManager.listAllClient());
        return "client/clients";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("client/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("client", clientManager.getClientById(id));
        return "client/clientform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("client/delete/{id}")
    public String delete(@PathVariable Long id){
        clientManager.deleteClient(id);
        return "redirect:/clients";
    }
}
