package com.salcisne.androidteste.model;

import androidx.databinding.BaseObservable;

public class Produto extends BaseObservable {
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getQtdText() {
        return  "Qtd:" + quantidade;
    }

    private String nome;
    private String local;
    private int quantidade;

    public Produto(String nome, String local, int quantidade) {
        this.nome = nome;
        this.local = local;
        this.quantidade = quantidade;
    }

}
