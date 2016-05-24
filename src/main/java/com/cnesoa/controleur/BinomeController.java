package com.cnesoa.controleur;

import com.cnesoa.config.DateEditor;
import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Person.Eleve;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.Person.EleveManager;
import com.cnesoa.validator.BinomeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * Created by Maxime on 13/04/2016.
 */

@Controller
public class BinomeController {

    //Autowiring of beans

    @Autowired
    private BinomeManager binomeManager;

    @Autowired
    private EleveManager eleveManager;

    /*________________________________________________*/

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("binome/new")
    public String newBinome(Model model){
        model.addAttribute("binome", new Binome());
        model.addAttribute("eleves", eleveManager.listAllEleve());
        return "binome/binomeform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "binome", method = RequestMethod.POST)
    public String saveBinome(Binome binome, BindingResult result){
        BinomeValidator binomeValidator = new BinomeValidator();
        binomeValidator.validate(binome, result);
        binomeManager.saveBinome(binome);
        return "redirect:/binome/" + binome.getId();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("binome/{id}")
    public String showBinome(@PathVariable Long id, Model model){
        model.addAttribute("binome", binomeManager.getBinomeById(id));
        return "binome/binomeshow";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/binomes", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("binomes", binomeManager.listAllBinome());
        return "binome/binomes";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("binome/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("binome", binomeManager.getBinomeById(id));
        model.addAttribute("eleves", eleveManager.listAllEleve());
        return "binome/binomeform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("binome/delete/{id}")
    public String delete(@PathVariable Long id){
        binomeManager.deleteBinome(id);
        return "redirect:/binomes";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        binder.registerCustomEditor(Eleve.class, "eleve", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Eleve ch = eleveManager.getEleveById(Long.parseLong(text));
                setValue(ch);
            }
        });
    }
}
