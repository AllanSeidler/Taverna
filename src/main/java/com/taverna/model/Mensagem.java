package com.taverna.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.springframework.context.annotation.Primary;

import java.util.Date;


@Entity

public class Mensagem {

    @EmbeddedId
    private MensagemID mensagemID;

    @Column
    String conteudo;



    String getConteudo(){
         return this.conteudo;
    }
    void setConteudo(String mail){
        this.conteudo = mail;
    }

}
