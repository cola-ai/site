/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Érico de Souza Loewe
 */
@Entity
@Table(name = "TRAJETO")
public class Trajeto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_TRAJETO_SEQ")
    @SequenceGenerator(name = "ID_TRAJETO_SEQ", sequenceName = "ID_TRAJETO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_TRAJETO")
    private Long id;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "LO_TRAJETO", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_TRAJETO_GEOLOCALIZACAO_LO"))
    private Geolocalizacao localizacao;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PA_TRAJETO", referencedColumnName = "ID_PASSO_DE_ROTA", foreignKey = @ForeignKey(name = "FK_TRAJETO_GEOLOCALIZACAO_PA"))
    private PassoDeRota passo;

    public Trajeto() {
    }

    public Trajeto(Geolocalizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Trajeto(Geolocalizacao localizacao, PassoDeRota passo) {
        this.localizacao = localizacao;
        this.passo = passo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Geolocalizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Geolocalizacao localizacao) {
        this.localizacao = localizacao;
    }

    public PassoDeRota getPasso() {
        return passo;
    }

    public void setPasso(PassoDeRota passo) {
        this.passo = passo;
    }
}
