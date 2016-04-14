package com.cnesoa.controleur;

import com.cnesoa.config.DateEditor;
import com.cnesoa.domain.Animal;
import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Consultation;
import com.cnesoa.domain.Professeur;
import com.cnesoa.manager.AnimalManager;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.ConsultationManager;
import com.cnesoa.manager.ProfesseurManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * Created by Maxime on 08/04/2016.
 */

@Controller
public class ConsultationController {

    private ConsultationManager consultationManager;

    private BinomeManager binomeManager;

    private ProfesseurManager professeurManager;

    private AnimalManager animalManager;

    @Autowired
    public void setAnimalManager(AnimalManager animalManager){
        this.animalManager = animalManager;
    }

    @Autowired
    public void setConsultationManager(ConsultationManager consultationManager){
        this.consultationManager = consultationManager;
    }

    @Autowired
    public void setBinomeManager(BinomeManager binomeManager){
        this.binomeManager = binomeManager;
    }

    @Autowired
    public void setProfesseurManager(ProfesseurManager professeurManager){
        this.professeurManager = professeurManager;
    }

    @RequestMapping("animal/{animalId}/consultation/new")
    public String newConsultation(@PathVariable Long animalId, Model model){
        Consultation consultation = new Consultation(animalManager.getAnimalById(animalId));
        model.addAttribute("consultation", consultation);
        model.addAttribute("binomes", binomeManager.listAllBinome());
        model.addAttribute("professeurs", professeurManager.listAllProfesseur());
        return "consultation/consultationform";
    }

    @RequestMapping(value = "consultation", method = RequestMethod.POST)
    public String saveConsultation(@Valid Consultation consultation, BindingResult bindingResult){
        consultationManager.saveConsultation(consultation);
        return "redirect:/consultation/" + consultation.getId();
    }

    @RequestMapping("consultation/{id}")
    public String showConsultation(@PathVariable Long id, Model model){
        model.addAttribute("consultation", consultationManager.getConsultationById(id));
            return "consultation/consultationshow";
    }

//    @RequestMapping(value = "consultations", method = RequestMethod.GET)
//    public String list(Model model){
//        model.addAttribute("consultations", consultationManager.listAllConsultation());
//        return "consultation/consultations";
//    }

    @RequestMapping("/consultations/{id}")
    public String listAnimal(@PathVariable Long id, Model model){
        Animal animal = animalManager.getAnimalById(id);
        model.addAttribute("consultations", animal.getConsultations());
        return "consultation/consultations";
    }

    @RequestMapping("consultation/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("consultation", consultationManager.getConsultationById(id));
        model.addAttribute("binomes", binomeManager.listAllBinome());
        model.addAttribute("professeurs", professeurManager.listAllProfesseur());
        return "consultation/consultationform";
    }

    @RequestMapping("consultation/delete/{id}")
    public String delete(@PathVariable Long id){
        consultationManager.deleteConsultation(id);
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        binder.registerCustomEditor(Binome.class, "binome", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Binome ch = binomeManager.getBinomeById(Long.parseLong(text));
                setValue(ch);
            }
        });
        binder.registerCustomEditor(Professeur.class, "professeur", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Professeur ch = professeurManager.getProfesseurById(Long.parseLong(text));
                setValue(ch);
            }
        });
        binder.registerCustomEditor(Consultation.class, "consultation", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Consultation ch = consultationManager.getConsultationById(Long.parseLong(text));
                setValue(ch);
            }
        });
    }


}
