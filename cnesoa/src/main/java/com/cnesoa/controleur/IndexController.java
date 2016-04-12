package com.cnesoa.controleur;

import com.cnesoa.config.DateEditor;
import com.cnesoa.domain.Consultation;
import com.cnesoa.manager.impl.ConsultationManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ConsultationManagerImpl consultationManager;


    @Autowired
    private void setConsultationManager(ConsultationManagerImpl consultationManager){
        this.consultationManager = consultationManager;
    }

    @RequestMapping("/")
    String index(Model model){
        model.addAttribute("consultations", consultationManager.toListEvent(consultationManager.listAllConsultation()));
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
