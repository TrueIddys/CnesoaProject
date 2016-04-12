package com.cnesoa.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maxime on 08/04/2016.
 */

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String PATH = ".error";

    @RequestMapping(value = PATH)
    public void showError(){

    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
