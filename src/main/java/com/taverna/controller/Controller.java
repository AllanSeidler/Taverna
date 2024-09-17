package com.taverna.controller;


import com.taverna.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;

public class Controller {
    public boolean checkLogin(HttpServletRequest request){
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("USUARIO_LOGADO");
        return usuarioLogado!=null;
    }
}
