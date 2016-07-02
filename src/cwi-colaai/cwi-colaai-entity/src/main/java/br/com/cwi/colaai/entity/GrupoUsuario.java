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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Alycio
 */
@Entity
@Table(name = "GRUPO_USUARIO")
public class GrupoUsuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GRUPO_USUARIO_SEQ")
    @SequenceGenerator(name = "ID_GRUPO_USUARIO_SEQ", sequenceName = "ID_GRUPO_USUARIO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO_USUARIO")
    private Long id; 
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne(optional = false)
    private Grupo grupo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
