package com.taverna.controller;

import com.taverna.model.Usuario;
import com.taverna.repository.AmizadeRepository;
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

import java.util.List;

@Controller
public class PerfilController {
    private final String USUARIO_LOGADO = "USUARIO_LOGADO";

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InteresseRepository interesseRepository;
    @Autowired
    private AmizadeRepository amizadeRepository;

    /**
     *  @author JALPassini
     *  @author AllanSeidler
     *
     *  @implNote Verifica se o usuario está logado.
     *  @return Mostra o perfil do usuario logado.
     */

    @GetMapping("/perfil")
    public String mostrarPerfilProprio(Model model, HttpServletRequest request) {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);
        return mostrarPerfil(usuarioLogado.getId(),model,request);
    }

    /**
     *  @author JALPassini
     *  @author AllanSeidler
     *
     *  @implNote verifica se o usuário com o id dado é o mesmo
     *  que está logado. Em caso afirmativo, o botão de edição
     *  e a lista de amigos são habilitadas.
     *
     *  @return Mostra o perfil de um usuário especifico.
     */
    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable Integer id, Model model, HttpServletRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

        if (usuario == null)  return "perfil";

        model.addAttribute("dono", usuario.equals(usuarioLogado));
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    /**
     *  @author JALPassini
     *  @implNote Ajeitar o DS "Editar Perfil" para que se
     *  encaixe dentro da descrição desse método.
     *  @return Mostra o perfil do usuário com campos editáveis.
     */
    @GetMapping("/editar-perfil")
    public String editarPerfil(HttpServletRequest request, Model model) {
        Usuario usuario = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

        if (usuario == null) {
            return "login";
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
    @PostMapping("/salvar-perfil")
    public String salvarPerfil(@Valid Usuario usuario,
                               BindingResult result, Model model,
                               @RequestParam("cidade") String cidade,
                               @RequestParam("estado") String estado,
                               HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("interesses_disponiveis", interesseRepository.findAll());
            return "editarPerfil";
        }

        Usuario usuarioExistente = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);
        if (usuarioExistente == null) {
            return "login";
        }

        // Atualiza os campos editáveis do usuário
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setInteresses(usuario.getInteresses());
        usuarioExistente.setEndereco("{\"cidade\":\"" + cidade + "\",\"estado\":\"" + estado + "\"}");

        usuarioRepository.save(usuarioExistente);
        return "redirect:/perfil";
    }

    @GetMapping("/ver-amigos")
    public String mostrarAmigos(Model model, HttpServletRequest request){
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);
        if (usuarioLogado == null)  return "login";

        List<Usuario> amigos = amizadeRepository.findAllFriends(usuarioLogado.getId());

        model.addAttribute("usuario", usuarioLogado);
        model.addAttribute("amigos",amigos);

        return "amigos";
    }

}
