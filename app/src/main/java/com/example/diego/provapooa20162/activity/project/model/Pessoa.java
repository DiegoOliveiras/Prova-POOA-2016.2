package com.example.diego.provapooa20162.activity.project.model;


import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Diego on 23/03/2017.
 */

public class Pessoa extends SugarRecord {
    private Long id;

    private String nome, email, senha;
    private int pontos=0;

    public Pessoa(){}

    public Pessoa(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void addPontos(int pontos){
        this.pontos += pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}
