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
     * Simplismente o menu.
     * */
    @GetMapping("/")
    public String menu(){
        return "index";
    }
}
