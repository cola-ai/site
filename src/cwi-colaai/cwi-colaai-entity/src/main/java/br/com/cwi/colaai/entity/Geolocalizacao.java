
package br.com.cwi.colaai.entity;

import br.com.cwi.colaai.entity.view_model.GeolocalizacaoViewModel;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Érico de Souza Loewe
 */
@Entity
@Table(name = "GEOLOCALIZACAO")
public class Geolocalizacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEOLOCALIZACAO")
    @SequenceGenerator(name = "SEQ_GEOLOCALIZACAO", sequenceName = "SEQ_GEOLOCALIZACAO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_GEOLOCALIZACAO")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "LT_GEOLOCALIZACAO")
    private Double latitude;
    
    @Basic(optional = false)
    @Column(name = "LN_GEOLOCALIZACAO")
    private Double longitude;
    
    @OneToMany(mappedBy="localizacao", cascade = CascadeType.ALL)
    private List<Trajeto> trajetorias;
    
    @OneToMany(mappedBy="pontoInicio", cascade = CascadeType.ALL)
    private List<PassoDeRota> pontosDeInicio;
    
    @OneToMany(mappedBy="pontoFim", cascade = CascadeType.ALL)
    private List<PassoDeRota> pontosFim;
    
    @OneToMany(mappedBy="localizacaoInicio", cascade = CascadeType.ALL)
    private List<PassoDeRota> localizacoesInicio;
    
    @OneToMany(mappedBy="localizacaoFim", cascade = CascadeType.ALL)
    private List<PassoDeRota> localizacoesFim;
    
    @OneToMany(mappedBy="localizacao", cascade = CascadeType.ALL)
    private List<Local> locais;

    public Geolocalizacao() {
    }

    public Geolocalizacao(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Trajeto> getTrajetorias() {
        return trajetorias;
    }

    public void setTrajetorias(List<Trajeto> trajetorias) {
        this.trajetorias = trajetorias;
    }

    public List<PassoDeRota> getPontosDeInicio() {
        return pontosDeInicio;
    }

    public void setPontosDeInicio(List<PassoDeRota> pontosDeInicio) {
        this.pontosDeInicio = pontosDeInicio;
    }

    public List<PassoDeRota> getPontosFim() {
        return pontosFim;
    }

    public void setPontosFim(List<PassoDeRota> pontosFim) {
        this.pontosFim = pontosFim;
    }

    public List<PassoDeRota> getLocalizacoesInicio() {
        return localizacoesInicio;
    }

    public void setLocalizacoesInicio(List<PassoDeRota> localizacoesInicio) {
        this.localizacoesInicio = localizacoesInicio;
    }

    public List<PassoDeRota> getLocalizacoesFim() {
        return localizacoesFim;
    }

    public void setLocalizacoesFim(List<PassoDeRota> localizacoesFim) {
        this.localizacoesFim = localizacoesFim;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }
    
    public GeolocalizacaoViewModel toViewModel(){
        return new GeolocalizacaoViewModel(latitude, longitude);
    }
}
