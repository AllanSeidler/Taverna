package com.taverna.controller;

import com.taverna.model.Mensagem;
import com.taverna.model.MensagemID;
import com.taverna.model.Usuario;
import com.taverna.repository.AmizadeRepository;
import com.taverna.repository.MensagemRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AllanSeidler
 *
 * @implNote
 * Controle do chat ficará aqui
 * */
@Controller
public class ChatController {

    @Autowired
    private MensagemRepository mensagemRepository;
    @Autowired
    private AmizadeRepository amizadeRepository;

    private static final String USUARIO_LOGADO = "USUARIO_LOGADO";
    private static final String MENSAGENS = "MENSAGENS";

    /**
     * @author AllanSeidler
     *
     * @implNote
     * Abre o chat do usuario locado com o usuario ID=id.
     * */

    @GetMapping("/conversar/{id}")
    public String conversarCom(@PathVariable int id, Model model, HttpServletRequest request){
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);
        if(usuarioLogado!=null) {
            Usuario usuario2 = amizadeRepository.findFriendById(id, usuarioLogado.getId());
//            System.out.println("usuario2: "+usuario2);

            if (usuario2 != null) {
                model.addAttribute("usuario1", usuarioLogado);
                model.addAttribute("usuario2", usuario2);
                List<Mensagem> mensagens = mensagemRepository.findMensagensByUsers(usuarioLogado.getId(), usuario2.getId());
                System.out.println(mensagens);
                model.addAttribute("mensagens", mensagens);
            } else return "erroChat";

        } else return "redirect:/login";

        return "chat";
    }

    @PostMapping("/enviar-mensagem/{id}")
    public String enviarMensagem(@PathVariable int id, HttpServletRequest request,
                                 @ModelAttribute("mensagem") String mensagem, Model model){
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(USUARIO_LOGADO);

        if(usuarioLogado!=null) {
            Usuario usuario2 = amizadeRepository.findFriendById(id, usuarioLogado.getId());

            if (usuario2 != null) {
                System.out.println(mensagem);
                model.addAttribute("usuario1", usuarioLogado);
                model.addAttribute("usuario2", usuario2);
                List<Mensagem> mensagens = mensagemRepository.findMensagensByUsers(usuarioLogado.getId(), usuario2.getId());

                Mensagem mensagem1 = new Mensagem();
                MensagemID mensagemID = new MensagemID();
                mensagemID.setRemetente(usuarioLogado.getId());
                mensagemID.setDestinatario(usuario2.getId());
                mensagemID.setData(LocalDateTime.now());
                mensagem1.setMensagemID(mensagemID);
                mensagem1.setConteudo(mensagem);

                System.out.println(mensagem1);
                mensagemRepository.save(mensagem1);


                mensagens.add(mensagem1);

                model.addAttribute("mensagens", mensagens);
            }
        } else return "/login";

        return "chat";
    }


}

