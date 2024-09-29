package com.taverna.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventoController {


    @GetMapping("/eventos")
    public String mostrarEventos(){
        return "eventos";
    }
 }
