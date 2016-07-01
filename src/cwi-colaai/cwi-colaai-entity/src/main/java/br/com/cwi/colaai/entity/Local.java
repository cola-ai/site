
package br.com.cwi.colaai.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Érico de Souza Loewe
 */
@Entity
@Table(name = "LOCAL_ITINERARIO")
public class Local implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_LOCAL_ITINERARIO_SEQ")
    @SequenceGenerator(name = "ID_LOCAL_ITINERARIO_SEQ", sequenceName = "ID_LOCAL_ITINERARIO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_LOCAL_ITINERARIO")
    private Long id;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "LO_LOCAL_ITINERARIO", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_LOCAL_GEOLOCALIZACAO"))
    private Geolocalizacao localizacao;
    
    @Basic(optional = false)
    @Column(name = "PA_LOCAL_ITINERARIO")
    private String pais;
    
    @Basic(optional = false)
    @Column(name = "ES_LOCAL_ITINERARIO")
    private String estado;
    
    @Basic(optional = false)
    @Column(name = "CI_LOCAL_ITINERARIO")
    private String cidade;
    
    @Basic(optional = false)
    @Column(name = "CE_LOCAL_ITINERARIO")
    private String cep;
    
    @Basic(optional = false)
    @Column(name = "BA_LOCAL_ITINERARIO")
    private String bairro;
    
    @Basic(optional = false)
    @Column(name = "RU_LOCAL_ITINERARIO")
    private String rua;
    
    @Basic(optional = false)
    @Column(name = "NU_LOCAL_ITINERARIO")
    private String numero;
    
    @OneToMany(mappedBy="origem", cascade = CascadeType.ALL)
    private List<Itinerario> origens;
    
    @OneToMany(mappedBy="destino", cascade = CascadeType.ALL)
    private List<Itinerario> destinos;

    public Local() {
    }

    public Local(Long id, Geolocalizacao localizacao, String pais, String estado, String cidade, String cep, String bairro, String rua, String numero, List<Itinerario> origens, List<Itinerario> destinos) {
        this.id = id;
        this.localizacao = localizacao;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.origens = origens;
        this.destinos = destinos;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Itinerario> getOrigens() {
        return origens;
    }

    public void setOrigens(List<Itinerario> origens) {
        this.origens = origens;
    }

    public List<Itinerario> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<Itinerario> destinos) {
        this.destinos = destinos;
    }
}
