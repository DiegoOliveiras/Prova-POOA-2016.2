package com.example.diego.provapooa20162.activity.project.model;


import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Diego on 23/03/2017.
 */

public class Tarefa extends SugarRecord {
    private Long id;

    private String titulo, descricao, dataInicio, prazo, dataTermino, observacao;
    private Grupo grupo;
    private Pessoa pessoa;
    private int pontos, status;

    public Tarefa(){}

    public Tarefa (String titulo, String descricao, String prazo, Grupo grupo){
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.grupo = grupo;

        dataInicio = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Random rand = new Random();
        pontos = rand.nextInt(100)+1;
        status=0;
    }

    public void concluirTarefa(Pessoa p, String observacao){
        pessoa = p;
        this.observacao = observacao;
        dataTermino = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        status=1;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

