package com.example.diego.provapooa20162.activity.project.model;


import com.orm.SugarRecord;

/**
 * Created by Diego on 23/03/2017.
 */

public class Grupo extends SugarRecord {
    private Long id;

    private String nome, descricao;
    private Pessoa gerente;
    private Pessoa[] membros;
    private int pontos=0;

    public Grupo(){}

    public Grupo(String nome, String descricao, Pessoa gerente){
        this.nome = nome;
        this.descricao = descricao;
        this.gerente = gerente;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getGerente() {
        return gerente;
    }

    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    public Pessoa[] getMembros() {
        return membros;
    }

    public void setMembros(Pessoa[] membros) {
        this.membros = membros;
    }
}

