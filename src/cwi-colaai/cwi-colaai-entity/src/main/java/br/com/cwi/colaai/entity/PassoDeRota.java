
package br.com.cwi.colaai.entity;

import br.com.cwi.colaai.entity.view_model.PassoDeRotaViewModel;
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
@Table(name = "PASSO_DE_ROTA")
public class PassoDeRota implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PASSO_DE_ROTA")
    @SequenceGenerator(name = "SEQ_PASSO_DE_ROTA", sequenceName = "SEQ_PASSO_DE_ROTA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_PASSO_DE_ROTA")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "DU_PASSO_DE_ROTA")
    private String duracao;
    
    @Basic(optional = false)
    @Column(name = "DI_PASSO_DE_ROTA")
    private String distancia;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_GEOLOCALIZACAO_PI", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_PI"))
    private Geolocalizacao pontoInicio;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_GEOLOCALIZACAO_LI", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_LI"))
    private Geolocalizacao localizacaoInicio;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_GEOLOCALIZACAO_PF", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_PF"))
    private Geolocalizacao pontoFim;
    
    @JoinColumn(name = "ID_GEOLOCALIZACAO_LF", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_LF"))
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)    
    private Geolocalizacao localizacaoFim;
    
    @OneToMany(mappedBy="passo", cascade = CascadeType.ALL)
    private List<Trajeto> trajetoria;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "RT_PASSO_DE_ROTA", referencedColumnName = "ID_ROTA", foreignKey = @ForeignKey(name = "FK_PDR_ROTA_RT"))
    private Rota rota;

    public PassoDeRota() {
    }

    public PassoDeRota(String duracao, String distancia, Geolocalizacao pontoInicio, Geolocalizacao localizacaoInicio, Geolocalizacao pontoFim, Geolocalizacao localizacaoFim, Rota rota) {
        this.duracao = duracao;
        this.distancia = distancia;
        this.pontoInicio = pontoInicio;
        this.localizacaoInicio = localizacaoInicio;
        this.pontoFim = pontoFim;
        this.localizacaoFim = localizacaoFim;
        this.rota = rota;
    }

    public PassoDeRota(String duracao, String distancia, Geolocalizacao pontoInicio, Geolocalizacao localizacaoInicio, Geolocalizacao pontoFim, Geolocalizacao localizacaoFim, List<Trajeto> trajetoria) {
        this.duracao = duracao;
        this.distancia = distancia;
        this.pontoInicio = pontoInicio;
        this.localizacaoInicio = localizacaoInicio;
        this.pontoFim = pontoFim;
        this.localizacaoFim = localizacaoFim;
        this.trajetoria = trajetoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public Geolocalizacao getPontoInicio() {
        return pontoInicio;
    }

    public void setPontoInicio(Geolocalizacao pontoInicio) {
        this.pontoInicio = pontoInicio;
    }

    public Geolocalizacao getLocalizacaoInicio() {
        return localizacaoInicio;
    }

    public void setLocalizacaoInicio(Geolocalizacao localizacaoInicio) {
        this.localizacaoInicio = localizacaoInicio;
    }

    public Geolocalizacao getPontoFim() {
        return pontoFim;
    }

    public void setPontoFim(Geolocalizacao pontoFim) {
        this.pontoFim = pontoFim;
    }

    public Geolocalizacao getLocalizacaoFim() {
        return localizacaoFim;
    }

    public void setLocalizacaoFim(Geolocalizacao localizacaoFim) {
        this.localizacaoFim = localizacaoFim;
    }

    public List<Trajeto> getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(List<Trajeto> trajetoria) {
        this.trajetoria = trajetoria;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }
    
}
