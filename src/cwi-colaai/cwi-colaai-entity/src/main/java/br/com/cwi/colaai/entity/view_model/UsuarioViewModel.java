/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.SexoPessoa;
import br.com.cwi.colaai.entity.Usuario;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author erico.loewe
 */
public class UsuarioViewModel {
    
    private Long idUsuario;

    
    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    @Pattern(regexp="[a-b,A-Z, ,-]+", message="Nome não pode conter números!")
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
    
    private String foto;
    
    @NotNull
    private SexoPessoa sexo;

    public UsuarioViewModel() {
    }

    public UsuarioViewModel(Long idUsuario, String nome, String email, String senha, String foto) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
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
