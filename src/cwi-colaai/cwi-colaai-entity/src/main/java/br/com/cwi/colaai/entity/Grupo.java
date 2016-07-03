/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Alycio
 */
@Entity
@Table(name = "GRUPO")
public class Grupo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GRUPO_SEQ")
    @SequenceGenerator(name = "ID_GRUPO_SEQ", sequenceName = "ID_GRUPO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO")
    private Long id; 
    
    @JoinColumn(name = "ID_USUARIO_LIDER", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario liderGrupo;
    
    @Basic(optional = false)
    @Column(name ="QTD_VAGAS")
    private Integer quantidadeVagas;
    
    @OneToMany(mappedBy="grupo", targetEntity = Itinerario.class)
    private List<Itinerario> itinerarios;
    
    @OneToMany(mappedBy="grupo")
    private List<GrupoUsuario> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getLiderGrupo() {
        return liderGrupo;
    }

    public void setLiderGrupo(Usuario liderGrupo) {
        this.liderGrupo = liderGrupo;
    }

    public int getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(int quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }
    
    
}
