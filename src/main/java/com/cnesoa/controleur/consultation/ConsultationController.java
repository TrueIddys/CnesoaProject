package com.cnesoa.controleur.consultation;

import com.cnesoa.config.AdvancedDateEditor;
import com.cnesoa.domain.Animal.Animal;
import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Consultation.Consultation;
import com.cnesoa.domain.Person.Professeur;
import com.cnesoa.manager.Animal.AnimalManager;
import com.cnesoa.manager.BinomeManager;
import com.cnesoa.manager.Consultation.ConsultationManager;
import com.cnesoa.manager.CurrentUserManager;
import com.cnesoa.manager.Person.ProfesseurManager;
import com.cnesoa.validator.ConsultationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //Autowiring of beans

    @Autowired
    private ConsultationManager consultationManager;

    @Autowired
    private BinomeManager binomeManager;

    @Autowired
    private ProfesseurManager professeurManager;

    @Autowired
    private AnimalManager animalManager;

    @Autowired
    private CurrentUserManager currentUserManager;

    /*_________________________________________________*/

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("animal/{animalId}/consultation/new")
    public String newConsultation(@PathVariable Long animalId, Model model){
        Consultation consultation = new Consultation();
        consultation.setAnimal(animalManager.getAnimalById(animalId));
        model.addAttribute("consultation", consultation);
        model.addAttribute("binomes", binomeManager.listAllBinome());
        model.addAttribute("professeurs", professeurManager.listAllProfesseur());
        model.addAttribute("lastBinome", animalManager.lastBinome(animalId).getName());
        return "consultation/consultationform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "consultation", method = RequestMethod.POST)
    public String saveConsultation(@Valid Consultation consultation, BindingResult bindingResult){
        ConsultationValidator consultationValidator = new ConsultationValidator();
        consultationValidator.validate(consultation, bindingResult);
        if (consultation.getId() == null)
            consultation.initializeNestedObjects();
        consultationManager.saveConsultation(consultation);
        return "redirect:/consultation/" + consultation.getId();
    }


    @RequestMapping("consultation/{id}")
    public String showConsultation(@PathVariable Long id, Model model){
        if (currentUserManager.checkConsultation(id)){
            model.addAttribute("consultation", consultationManager.getConsultationById(id));
            return "consultation/consultationshow";
        }
        else
            throw new AccessDeniedException("L'utilisateur ne peut pas accéder à cette consultation");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/consultations/{id}")
    public String listAnimalConsultations(@PathVariable Long id, Model model){
        Animal animal = animalManager.getAnimalById(id);
        model.addAttribute("consultations", animal.getConsultations());
        return "consultation/consultations";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("consultation/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Consultation consultation = consultationManager.getConsultationById(id);
        model.addAttribute("consultation", consultation);
        model.addAttribute("binomes", binomeManager.listAllBinome());
        model.addAttribute("professeurs", professeurManager.listAllProfesseur());
        model.addAttribute("lastBinome", animalManager.lastBinome(consultation.getAnimal().getId()).getName());
        return "consultation/consultationform";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("consultation/delete/{id}")
    public String delete(@PathVariable Long id){
        Long animalId = consultationManager.getConsultationById(id).getAnimal().getId();
        consultationManager.deleteConsultation(id);
        return "redirect:/animal/" +animalId;
    }

    /**
     * Used to transform the different class into a format readable by thymeleaf
     * @param binder
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new AdvancedDateEditor());
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
