package com.taverna.model;
import java.util.Date;

public class Evento {
    private Usuario responsavel;
    private String nome;
    private float inscricao;
    private String endereco;
    private Date data;
    private boolean presencial;
    private Interesse jogo;



    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getInscricao() {
        return inscricao;
    }

    public void setInscricao(float inscricao) {
        this.inscricao = inscricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isPresencial() {
        return presencial;
    }

    public void setPresencial(boolean presencial) {
        this.presencial = presencial;
    }

    public Interesse getJogo() {
        return jogo;
    }

    public void setJogo(Interesse jogo) {
        this.jogo = jogo;
    }
}
