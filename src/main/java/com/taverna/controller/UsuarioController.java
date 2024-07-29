package com.taverna.controller;

import com.taverna.model.Usuario;
import com.taverna.repository.InteresseRepository;
import com.taverna.repository.UsuarioRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author AllanSeidler
 *
 * @implNote
 * Classe para trabalhar com o controle do
 * cadastro e exclusão de usuarios apenas.
 *
 * @see com.taverna.model.Usuario
 * @see UsuarioRepository
 * @see InteresseRepository
 * */
@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InteresseRepository interesseRepository;
    /**
     * @author AllanSeidler
     *
     * @implNote
     * Ajeitar o DS "cadastro de usuário" para que se
     * encaixe dentro da descrição desse método.
     *
     * @return retorna a página de cadastro para o usuário.
     * */
    @GetMapping("/novo-usuario")
    public String mostrarFormNovoUsuario(Usuario usuario, Model model){
        model.addAttribute("interesses_disponiveis",interesseRepository.findAll());

        return "cadastroUsuario";
    }

    /**
     * @author AllanSeidler
     *
     * @implNote
     * Ajeitar o DS "cadastro de usuário" para que se
     * encaixe dentro da descrição desse método.
     *
     * @return retorna para o menu com o usuario logado (não implementado).
     * */
    @PostMapping("/adicionar-usuario")
    public String adicionarUsuario(@Valid Usuario usuario,
                                   @ModelAttribute("estado") String estado,
                                   @ModelAttribute("cidade") String cidade,
                                   BindingResult result){
        if(result.hasErrors()){
            return "CadastroUsuario";
        }
        // converte o endereço para json
        usuario.setEndereco("{\"cidade\":\""+cidade+"\",\"estado\":\""+estado+"\"}");

        usuarioRepository.save(usuario);
        return "redirect:/index";
    }
}
