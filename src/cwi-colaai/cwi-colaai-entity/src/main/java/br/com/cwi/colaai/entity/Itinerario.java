
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
 * @author Érico de Souza Loewe
 */
@Entity
@Table(name = "ITINERARIO")
public class Itinerario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_ITINERARIO_SEQ")
    @SequenceGenerator(name = "ID_ITINERARIO_SEQ", sequenceName = "ID_ITINERARIO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_ITINERARIO")
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "OR_ITINERARIO", referencedColumnName = "ID_LOCAL_ITINERARIO")
    private Local origem;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "DT_ITINERARIO", referencedColumnName = "ID_LOCAL_ITINERARIO")
    private Local destino;
    
    @Basic(optional = false)
    @Column(name = "HS_ITINERARIO")
    private String horarioSaida;
    
    @OneToMany(mappedBy="itinerario")
    private List<ItinerarioDiasDaSemana> diasDaSemana;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "RT_ITINERARIO", referencedColumnName = "ID_ROTA")
    private Rota rota;

    public Itinerario() {
    }

    public Itinerario(Long id, Local origem, Local destino, String horarioSaida, List<ItinerarioDiasDaSemana> diasDaSemana, Rota rota) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.horarioSaida = horarioSaida;
        this.diasDaSemana = diasDaSemana;
        this.rota = rota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Local getOrigem() {
        return origem;
    }

    public void setOrigem(Local origem) {
        this.origem = origem;
    }

    public Local getDestino() {
        return destino;
    }

    public void setDestino(Local destino) {
        this.destino = destino;
    }

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public List<ItinerarioDiasDaSemana> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(List<ItinerarioDiasDaSemana> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }
}
