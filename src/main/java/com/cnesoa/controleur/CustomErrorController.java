package com.cnesoa.controleur;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxime on 14/04/2016.
 */

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    /**
     * Catch random exception/error and redirect to custom error page
     * @param e
     * @param model
     * @return
     */
    @RequestMapping(value = PATH)
    public String error(Exception e, Model model) {
        System.out.println(e.getStackTrace());
        model.addAttribute("error", "Oups ! Le site a rencontré une erreur.");
        return "customerror";
    }

    /**
     * catch access denied exception launched by Spring Security
     * (those cannot be catched in GlobalExceptionHandler)
     * @param model
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDeniedException(Model model){
        System.out.println("Access denied");
        model.addAttribute("error", "Vous n'avez pas l'autorisation d'effectuer cette action.");
        return "customerror";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
