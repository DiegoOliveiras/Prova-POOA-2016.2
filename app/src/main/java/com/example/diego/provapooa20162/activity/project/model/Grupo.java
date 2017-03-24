package com.example.diego.provapooa20162.activity.project.model;


import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 23/03/2017.
 */

public class Grupo extends SugarRecord {
    private Long id;

    private String nome, descricao;
    private Pessoa gerente;
    private Pessoa p1, p2, p3;
    private int pontos=0;

    public Grupo(){}

    public Grupo(String nome, String descricao, Pessoa gerente){
        this.nome = nome;
        this.descricao = descricao;
        this.gerente = gerente;
        p1=null;
        p2=null;
        p3=null;
    }

    public Boolean addParticipante(Pessoa p){
        int ok = 1;
        if (p1 != null){
            if (p1.getId() == p.getId()){
                ok = 0;
            }
        }
        if (p2 != null){
            if (p2.getId() == p.getId()){
                ok = 0;
            }
        }
        if (p3 != null){
            if (p3.getId() == p.getId()){
                ok = 0;
            }
        }
        if (ok == 1){
            if (p1 == null){
                p1 = p;
                return true;
            }
            if (p2 == null){
                p2 = p;
                return true;
            }
            if (p3 == null){
                p1 = p;
                return true;
            }
        }
        return false;
    }

    public Boolean removeParticipante(Pessoa p){
        if (p1 != null){
            if (p1.getId() == p.getId()) {
                p1 = null;
                return true;
            }
        }
        if (p2 != null){
            if (p2.getId() == p.getId()){
                p2 = null;
                return true;
            }
        }
        if (p3 != null){
            if (p3.getId() == p.getId()){
                p3 = null;
                return true;
            }
        }
        return false;
    }

    public Pessoa getP1() {
        return p1;
    }

    public void setP1(Pessoa p1) {
        this.p1 = p1;
    }

    public void setP2(Pessoa p2) {
        this.p2 = p2;
    }

    public Pessoa getP3() {
        return p3;
    }

    public void setP3(Pessoa p3) {
        this.p3 = p3;
    }

    public Pessoa getP2() {
        return p2;
    }

    public List<Tarefa> getTarefas(){
        return Tarefa.find(Tarefa.class, "grupo = ?", String.valueOf(this.getId()));
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

}

