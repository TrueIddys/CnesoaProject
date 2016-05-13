package com.cnesoa.controleur;

import com.cnesoa.exceptions.MissingAttributeException;
import com.cnesoa.exceptions.NullObjectException;
import com.cnesoa.exceptions.impl.UserNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by Maxime on 26/04/2016.
 * Class used to catch exception and redirect the errors to custom error page with
 * a custom message created when the exception was thrown.
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public String usernameNotFoundException(Model model, UserNotFoundException e){
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
        model.addAttribute("error", "Ce nom d'utilisateur est inconnu.");
        return "customerror";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(Model model, AccessDeniedException e){
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
        model.addAttribute("error", e.getMessage());
        return "customerror";
    }

    @ExceptionHandler(MissingAttributeException.class)
    public String missingAttributeException(Model model, MissingAttributeException e) throws IOException, ServletException {
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
        model.addAttribute("error", e.getMessage());
        return "customerror";
    }

    @ExceptionHandler(NullObjectException.class)
    public String nullObjectException(Model model, NullObjectException e){
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
        model.addAttribute("error", e.getMessage());
        return "customerror";
    }

}
