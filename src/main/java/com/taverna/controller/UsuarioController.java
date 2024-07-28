package com.taverna.controller;

import com.taverna.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author "AllanSeidler"
 *
 * @implNote
 * Classe para trabalhar com o controle do
 * cadastro e exclusão de usuarios apenas.
 *
 * @see com.taverna.model.Usuario
 * @see UsuarioRepository
 * */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * @author AllanSeidler
     *
     * @implNote
     * Ajeitar o DS "cadastro de usuário" para que se
     * encaixe dentro da descrição desse método.
     *
     * @return retorna a página de cadastro para o usuário.
     * */
    @GetMapping("/cadastro_usuario")
    public String mostrarFormNovoUsuario(){
        return "cadastroUsuario";
    }
}
