package com.taverna.model;

import java.util.Date;

public class Mensagem {
    String conteudo;
    Date data;
    Usuario enviou;
    Usuario recebeu;
    String getConteudo(){
         return this.conteudo;
    }
    void setConteudo(String mail){
        this.conteudo = mail;
    }
    void setData(Date dia){
        this.data = dia;
    }
    Date getData(){
        return this.data;
    }
}
