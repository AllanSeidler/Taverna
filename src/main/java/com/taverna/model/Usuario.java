package com.taverna.model;

import jakarta.persistence.*;

import java.util.List;



@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 15)
    private String nome;

    @Column(unique = true, nullable = false, length = 40)
    private String login;

    @Column(nullable = false, length = 20)
    private String senha;

    @ManyToMany
    @JoinTable(name = "interesses_usuarios",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_interesse"))
    private List<Interesse> interesses;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Interesse> getInteresses() {
        return interesses;
    }

    public void setInteresses(List<Interesse> interesses) {
        this.interesses = interesses;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
