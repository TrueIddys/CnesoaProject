package com.cnesoa.controleur;

import com.cnesoa.config.DateEditor;
import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.manager.Consultation.impl.ConsultationManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * Created by Maxime on 30/03/2016.
 */

@Controller
public class IndexController {

    //Autowiring of beans

    @Autowired
    private ConsultationManagerImpl consultationManager;

    /*_________________________________________________*/

    @RequestMapping("/")
    String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("consultations", consultationManager.toListEvent(consultationManager.listUserConsultation()));
        }
        else {
            model.addAttribute("consultations", consultationManager.toListEvent(consultationManager.listAllConsultation()));
        }
        return "index";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        binder.registerCustomEditor(Consultation.class, "consultation", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Consultation ch = consultationManager.getConsultationById(Long.parseLong(text));
                setValue(ch);
            }
        });
    }
}
