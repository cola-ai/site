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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author diuly.barreto
 */

@Entity
@Table(name = "TOKEN")
public class Token implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_TOKEN_SEQ")
    @SequenceGenerator(name = "ID_TOKEN_SEQ", sequenceName = "ID_TOKEN_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_TOKEN")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "VL_TOKEN")
    private String valor;
    
    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = true)
    @Column(name = "ST_TOKEN")
    private StatusToken status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusToken getStatus() {
        return status;
    }

    public void setStatus(StatusToken status) {
        this.status = status;
    }
}
