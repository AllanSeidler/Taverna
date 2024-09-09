package com.taverna.controller;

import com.taverna.model.Usuario;
import com.taverna.repository.InteresseRepository;
import com.taverna.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     *  @author JALPassini
     *
     *  @impNote
     *  Ajeitar o DS "Ver Perfil" para que se
     *  encaixe dentro da descrição desse método.
     *
     *  @return retornar para o perfil de um usuario especifico.
     */

    // Exibir o perfil do usuário
    // Mostra o perfil de um usuário específico
    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable int id, Model model, HttpServletRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("USUARIO_LOGADO");

        if (usuario == null) {
            model.addAttribute("erro", "Usuário não encontrado.");
            return "erro_perfil";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarioLogado", usuarioLogado);
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
