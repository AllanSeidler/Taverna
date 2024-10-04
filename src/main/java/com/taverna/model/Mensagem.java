package com.taverna.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.util.Date;


@Entity
public class Mensagem {

    @EmbeddedId
    private MensagemID mensagemID;

    @Column
    private String conteudo;


    public String getConteudo(){
        return this.conteudo;
    }
    public void setConteudo(String mail){
        this.conteudo = mail;
    }

    public MensagemID getMensagemID() {
        return mensagemID;
    }

    public void setMensagemID(MensagemID mensagemID) {
        this.mensagemID = mensagemID;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "mensagemID=" + mensagemID +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}