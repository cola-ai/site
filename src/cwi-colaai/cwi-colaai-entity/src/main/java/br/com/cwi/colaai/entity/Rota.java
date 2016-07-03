
package br.com.cwi.colaai.entity;

import br.com.cwi.colaai.entity.view_model.RotaViewModel;
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
@Table(name = "ROTA")
public class Rota implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROTA")
    @SequenceGenerator(name = "SEQ_ROTA", sequenceName = "SEQ_ROTA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_ROTA")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "DU_ROTA")
    private String duracao;
    
    @Basic(optional = false)
    @Column(name = "DI_ROTA")
    private String distancia;
    
    @Basic(optional = false)
    @Column(name = "PO_ROTA", length = 2000)
    private String polilyne;
    
    @OneToMany(mappedBy="rota", cascade = CascadeType.ALL)
    private List<PassoDeRota> passos;
    
    @OneToMany(mappedBy="rota", cascade = CascadeType.ALL)
    private List<Itinerario> itinerarios;

    public Rota() {
    }

    public Rota(String duracao, String distancia, String polilyne, List<PassoDeRota> passos) {
        this.duracao = duracao;
        this.distancia = distancia;
        this.polilyne = polilyne;
        this.passos = passos;
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

    public String getPolilyne() {
        return polilyne;
    }

    public void setPolilyne(String polilyne) {
        this.polilyne = polilyne;
    }

    public List<PassoDeRota> getPassos() {
        return passos;
    }

    public void setPassos(List<PassoDeRota> passos) {
        this.passos = passos;
    }

    public List<Itinerario> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<Itinerario> itinerarios) {
        this.itinerarios = itinerarios;
    }
    
//    public RotaViewModel toViewModel(){
//        return new RotaViewModel(duracao, distancia, polilyne, passos);
//    }
}
