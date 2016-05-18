package com.cnesoa.controleur.person;

import com.cnesoa.domain.Person.User;
import com.cnesoa.exceptions.NotMatchingException;
import com.cnesoa.manager.CurrentUserManager;
import com.cnesoa.manager.Person.UserManager;
import com.cnesoa.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Maxime on 17/05/2016.
 */

@Controller
public class UserController {

    @Autowired
    private CurrentUserManager currentUserManager;

    @Autowired
    private UserManager userManager;

    @RequestMapping("passwordModif")
    public String passwordModif(@RequestParam("newPassword") String newPass,
                                @RequestParam("actualPassword") String actualPass,
                                @RequestParam("newPasswordConf") String newPassConf){
        User user = currentUserManager.getUser();
        if (! (new BCryptPasswordEncoder().matches(actualPass, user.getPasswordHash()))){
            throw new AccessDeniedException("Le mot de passe actuel n'est pas le bon.");
        }
        if (!newPass.equals(newPassConf)){
            throw new NotMatchingException("Les nouveaux mots de passes indiqués ne sont pas les mêmes.");
        }
        user.setPasswordHash(newPass);
        userManager.saveUser(user);
        return "redirect:/me";
    }

    @RequestMapping("password")
    public String goToPasswordModif(Model model){
        User user = currentUserManager.getUser();
        model.addAttribute("user", user);
        return "passwordmodif";
    }

    @RequestMapping("me")
    public String goToMe(){
        User user = currentUserManager.getUser();
        if (user.getRole().equals(Role.ROLE_ELEVE))
            return "redirect:/eleve/me";
        else if (user.getRole().equals(Role.ROLE_PROF))
            return "redirect:/professeur/me";
        else
            return "redirect:/";
    }
}
