package com.taverna.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    private final String USUARIO_LOGADO = "USUARIO_LOGADO";

    /**
     * @author AllanSeidler
     *
     * @implNote
     * Simplismente o menu.
     * */
    @GetMapping(value = {"/","/index"})
    public String menu(HttpServletRequest request){
        Object usuario = request.getSession().getAttribute(USUARIO_LOGADO);
        if(usuario==null)
            return "redirect:/login";
        else return "index";
    }
}
