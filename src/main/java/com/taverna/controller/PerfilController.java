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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PerfilController {
    private final String USUARIO_LOGADO = "USUARIO_LOGADO";

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InteresseRepository interesseRepository;

    /**
     *  @author JALPassini
     *  @author AllanSeidler
     *
     *  @implNote Verifica se o usuario está logado.
     *  @return Mostra o perfil do usuario logado.
     */

    // Mostra o perfil do usuário
    @GetMapping("/perfil")
    public String mostrarPerfilProprio(Model model, HttpServletRequest request) {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

        if(usuarioLogado==null) return "login";
        model.addAttribute("dono", true);
        model.addAttribute("usuario", usuarioLogado);
        System.out.println(usuarioLogado);

        return "perfil";
    }

    /**
     *  @author JALPassini
     *  @author AllanSeidler
     *
     *  @implNote verifica se o usuário com o id dado é o mesmo
     *  que está logado. Em caso afirmativo, o botão de edição
     *  é habilitado.
     *
     *  @return Mostra o perfil de um usuário especifico.
     */
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
     *  @implNote Ajeitar o DS "Editar Perfil" para que se
     *  encaixe dentro da descrição desse método.
     *  @return Mostra o perfil do usuário com campos editáveis.
     */
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

    /**
     *  @author JALPassini
     *  @implNote Salva alterações feitas no perfil.
     *  @return Redireciona para o caso '/perfil/{id}'.
     */
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
