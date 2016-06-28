/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.SexoPessoa;

/**
 *
 * @author erico.loewe
 */
public class UsuarioViewModel {
    
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;
    private String confirmarSenha;
    private String foto;
    private SexoPessoa sexo;

    public SexoPessoa getSexo() {
        return sexo;
    }

    public UsuarioViewModel(String nome, String email, String senha, String foto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
    public void setSexo(SexoPessoa sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }    
}
