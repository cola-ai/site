/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.entity.view_model;

import java.io.InputStream;

/**
 *
 * @author Érico de Souza Loewe
 */
public class ImagemViewModel {
    private String nome;
    private String nomeOriginal;
    private InputStream stream;

    public ImagemViewModel() {
    }

    public ImagemViewModel(String nome, String nomeOriginal, InputStream stream) {
        this.nome = nome;
        this.nomeOriginal = nomeOriginal;
        this.stream = stream;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }
}
