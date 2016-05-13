package com.cnesoa.controleur;

import com.cnesoa.repository.search.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Maxime on 10/05/2016.
 */
@Controller
public class SearchController {

    @Autowired
    private UserSearch userSearch;

    @RequestMapping("search")
    public String search(@RequestParam("search") String search, Model model){
        List searchResults = null;
        try {
            searchResults = userSearch.search(search);
        }
        catch (Exception e){
            throw e;
        }
        model.addAttribute("clients", userSearch.getResultsClients(searchResults));
        model.addAttribute("animaux", userSearch.getResultsAnimaux(searchResults));
        model.addAttribute("eleves", userSearch.getResultsEleves(searchResults));
        model.addAttribute("professeurs", userSearch.getResultsProfesseurs(searchResults));
        return "searchresults";
    }
}
