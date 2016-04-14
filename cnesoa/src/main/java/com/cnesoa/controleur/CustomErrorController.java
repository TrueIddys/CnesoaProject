package com.cnesoa.controleur;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maxime on 14/04/2016.
 */
public class CustomErrorController implements ErrorController {

    private final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String goToErrorPage(Exception e){
        System.out.println(e.getStackTrace());
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
