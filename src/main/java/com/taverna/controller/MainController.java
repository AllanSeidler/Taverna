package com.taverna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author AllanSeidler
 *
 * @implNote
 * Controle das páginas principais como o menu
 * ficarão aqui.
 * */

@Controller
public class MainController {

    /**
     * @author AllanSeidler
     *
     * @implNote
     * Simplismente o menu.
     * */
    @GetMapping(value = {"/","/index"})
    public String menu(){
        return "index";
    }
}
