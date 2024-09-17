package com.taverna.controller;

import com.taverna.model.Usuario;
import com.taverna.repository.InteresseRepository;
import com.taverna.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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
    private final String USUARIO_LOGADO = "USUARIO_LOGADO";

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InteresseRepository interesseRepository;

    /**
     * @author AllanSeidler
     * @implNote Mostra um formulario para o usuário se cadastrar.
     * @return Retorna a página de cadastro para o usuário.
     * */
    @GetMapping("/novo-usuario")
    public String mostrarFormNovoUsuario(Usuario usuario, Model model){
        model.addAttribute("interesses_disponiveis",interesseRepository.findAll());
        return "cadastroUsuario";
    }

    /**
     * @author AllanSeidler
     * @implNote Ajeitar o DS "cadastro de usuário" para que se
     * encaixe dentro da descrição desse método.
     * @return Retorna para o menu com o usuario logado.
     * */
    @PostMapping("/adicionar-usuario")
    public String adicionarUsuario(@Valid Usuario usuario,
                                   @ModelAttribute("estado") String estado,
                                   @ModelAttribute("cidade") String cidade,
                                   BindingResult result,
                                   HttpServletRequest request){

        if(result.hasErrors()){return "cadastroUsuario";}
        // converte o endereço para json
        usuario.setEndereco("{\"cidade\":\""+cidade+"\",\"estado\":\""+estado+"\"}");
        request.getSession().setAttribute(USUARIO_LOGADO,usuario);

        usuarioRepository.save(usuario);
        return "redirect:/index";
    }

    /**
     * @author AllanSeidler
     * @implNote Verifica se há um usuário com os dados cadastrado.
     * @return Retorna para o menu com o usuario logado.
     * */
    @PostMapping("/confirmar-login")
    public String confirmarLogin(@ModelAttribute("login") String login,
                                 @ModelAttribute("senha") String senha,
                                 HttpServletRequest request){

        Usuario u = usuarioRepository.findUserByLogin(login,senha);
        if(u==null) return "redirect:/login";

        request.getSession().setAttribute(USUARIO_LOGADO,u);
        return "redirect:/index";
    }

    /**
     * @author AllanSeidler
     * @return Mostra a tela de login.
     * */
    @GetMapping("/login")
    public String MostrarTelaLogin(){return "login";}

}
