package com.taverna.model;

import jakarta.persistence.*;

import java.util.Set;

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
    @JoinTable(name = "interesses_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_interesse"))
    private Set<Interesse> interesses;

    @Column
    private String endereco;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", interesses=" + interesses +
                ", endereco='" + endereco + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Usuario){
            return this.id==((Usuario) obj).id;
        } else return false;
    }

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

    public Set<Interesse> getInteresses() {
        return interesses;
    }

    public void setInteresses(Set<Interesse> interesses) {
        this.interesses = interesses;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
