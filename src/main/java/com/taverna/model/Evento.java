package com.taverna.model;
import java.util.Date;

public class Evento {
    String nome;
    float inscricao;
    String endereco;
    Date data;
    boolean presencial;
    Object interesse;         //Substituir Object por classe Interesse
    Usuario dono;
    String getNome() {
        return this.nome;
    }
    void setNome(String name){
        this.nome = name;
    }
    float getInscricao(){
        return this.inscricao;
    }
    void setInscricao(float ins){
        this.inscricao = ins;
    }
    String getEndereco(){
        return this.endereco;
    }
    void setEndereco(String adr){
        this.endereco = adr;
    }
    void setData(Date dia){
        this.data = dia;
    }
    Date getData(){
        return this.data;
    }
    boolean getPresencial(){
        return this.presencial;
    }
    void setPresencial(boolean aoVivo){
        this.presencial = aoVivo;
    }
    Object getJogo(){               //Substituir Object por classe Interesse
        return this.interesse;
    }
    void setJogo(Object game){      //Substituir Object por classe Interesse
        this.interesse = game;
    }

}
