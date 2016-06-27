/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author diuly.barreto
 */

@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PESSOA_SEQ")
    @SequenceGenerator(name = "ID_PESSOA_SEQ", sequenceName = "ID_PESSOA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_PESSOA")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "NM_PESSOA")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "SM_PESSOA")
    private String sobrenome;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "TP_SEXO_PESSOA")
    private SexoPessoa sexo;
    
    @Basic(optional = false)
    @Column(name = "TEL_PESSOA")
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SexoPessoa getSexo() {
        return sexo;
    }

    public void setSexo(SexoPessoa sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
