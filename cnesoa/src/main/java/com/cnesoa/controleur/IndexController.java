package com.cnesoa.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maxime on 30/03/2016.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "index";
    }
}
