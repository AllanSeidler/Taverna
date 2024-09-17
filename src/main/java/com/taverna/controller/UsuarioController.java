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


    @PostMapping("/confirmar-login")
    public String confirmarLogin(@ModelAttribute("login") String login,
                                 @ModelAttribute("senha") String senha,
                                 HttpServletRequest request){

        Usuario u = usuarioRepository.findUserByLogin(login,senha);
        if(u!=null){
            System.out.println(u);
            request.getSession().setAttribute(USUARIO_LOGADO,u);
        }
        return "redirect:/index";
    }
    @GetMapping("/login")
    public String MostrarTelaLogin(){return "login";}

    /**
     *  @author JALPassini
     * @author AllanSeidler
     *
     *  @impNote
     *  Ajeitar o DS "Ver Perfil" para que se
     *  encaixe dentro da descrição desse método.
     *
     *  @return retornar para o perfil de um usuario especifico.
     */

    // Mostra o perfil do usuário
    @GetMapping("/perfil")
    public String mostrarPerfilProprio(Model model, HttpServletRequest request) {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

        // usuario == usuarioLogado?
        model.addAttribute("dono", true);
        // dono do perfil
        model.addAttribute("usuario", usuarioLogado);
        System.out.println(usuarioLogado.getEndereco());

        return "perfil";
    }

    // Mostra o perfil de um usuário específico
    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable Integer id, Model model, HttpServletRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário não encontrado.");
            return "erro_perfil";
        }
        // usuario == usuarioLogado?
        model.addAttribute("dono", usuario.equals(usuarioLogado));
        // dono do perfil
        model.addAttribute("usuario", usuario);
        System.out.println(usuario.getEndereco());

        return "perfil";
    }

    /**
     *  @author JALPassini
     *
     *  @impNote
     *  Ajeitar o DS "Editar Perfil" para que se
     *  encaixe dentro da descrição desse método.
     *
     *  @return retornar para o perfil do proprio usuario.
     */

    // Exibe o formulário de edição de perfil
    @GetMapping("/editar-perfil/{id}")
    public String editarPerfil(@PathVariable int id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário não encontrado.");
            return "erro_perfil";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("interesses_disponiveis", interesseRepository.findAll());
        return "editarPerfil";
    }

    // Salva as alterações feitas no perfil
    @PostMapping("/salvar-perfil/{id}")
    public String salvarPerfil(@PathVariable int id, @Valid Usuario usuario,
                               BindingResult result, Model model,
                               @RequestParam("cidade") String cidade,
                               @RequestParam("estado") String estado) {
        if (result.hasErrors()) {
            model.addAttribute("interesses_disponiveis", interesseRepository.findAll());
            return "editarPerfil";
        }

        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            model.addAttribute("erro", "Usuário não encontrado.");
            return "erro_perfil";
        }

        // Atualiza os campos editáveis do usuário
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setInteresses(usuario.getInteresses());
        usuarioExistente.setEndereco("{\"cidade\":\"" + cidade + "\",\"estado\":\"" + estado + "\"}");

        usuarioRepository.save(usuarioExistente);
        model.addAttribute("mensagem", "Alterações realizadas com sucesso.");
        return "redirect:/perfil/" + id;
    }
}
