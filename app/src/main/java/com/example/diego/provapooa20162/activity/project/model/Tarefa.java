package com.example.diego.provapooa20162.activity.project.model;


import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Diego on 23/03/2017.
 */

public class Tarefa extends SugarRecord {
    private Long id;

    private String titulo, descricao, dataInicio, prazo, dataTermino, observacao;
    private Grupo grupo;
    private int pontos=0, status=0;

    public Tarefa(){}

    public Tarefa (String titulo, String descricao, String prazo, Grupo grupo){
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.grupo = grupo;

        dataInicio = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

