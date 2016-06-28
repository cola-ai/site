/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.SexoPessoa;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author erico.loewe
 */
public class UsuarioViewModel {
    
    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    private String nome;
    
    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    private String sobrenome;
    
    @NotNull
    @NotBlank
    @Size(min=11, max=255)
    private String telefone;
    
    @NotNull
    @NotBlank
    @Size(min=3, max=255)
    @Email
    private String email;
    
    @NotNull
    @NotBlank
    @Size(min=6, max=255)
    private String senha;
    
    @NotNull
    @NotBlank
    @Size(min=6, max=255)
    private String confirmarSenha;
    
    private String imagem;
    
    @NotNull
    private SexoPessoa sexo;

    public SexoPessoa getSexo() {
        return sexo;
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

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
}
