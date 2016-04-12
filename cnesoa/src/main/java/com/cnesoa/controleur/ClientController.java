package com.cnesoa.controleur;

import com.cnesoa.domain.Client;
import com.cnesoa.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxime on 30/03/2016.
 */

@Controller
public class ClientController {

    private ClientManager clientManager;

    @Autowired
    public void setClientManager(ClientManager clientManager){
        this.clientManager = clientManager;
    }

    @RequestMapping("client/new")
    public String newClient(Model model){
        model.addAttribute("client", new Client());
        return "client/clientform";
    }

    @RequestMapping(value = "client", method = RequestMethod.POST)
    public String saveClient(Client client){
        clientManager.saveClient(client);
        return "redirect:/client/" + client.getId();
    }

    @RequestMapping("client/{id}")
    public String showClient(@PathVariable Long id, Model model){
        model.addAttribute("client", clientManager.getClientById(id));
        return "client/clientshow";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("clients", clientManager.listAllClient());
        return "client/clients";
    }

    @RequestMapping("client/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("client", clientManager.getClientById(id));
        return "client/clientform";
    }

    @RequestMapping("client/delete/{id}")
    public String delete(@PathVariable Long id){
        clientManager.deleteClient(id);
        return "redirect:/clients";
    }
}
