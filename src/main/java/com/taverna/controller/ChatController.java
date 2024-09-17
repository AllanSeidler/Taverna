package com.taverna.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author AllanSeidler
 *
 * @implNote
 * Controle do chat ficará aqui
 * */
@Controller
public class ChatController {


    /**
     * @author AllanSeidler
     *
     * @implNote
     * Abre o chat do usuario locado com o usuario ID=id.
     * */
    @GetMapping("/conversa/{id}")
    public String conversarCom(@PathVariable int id, Model model){
        
        return "chat";
    }
}
