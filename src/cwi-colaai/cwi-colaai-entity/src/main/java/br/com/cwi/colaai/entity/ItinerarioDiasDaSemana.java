
package br.com.cwi.colaai.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "ITINERARIO_DIAS_DA_SEMANA")
public class ItinerarioDiasDaSemana implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_ITI_DDS_SEQ")
    @SequenceGenerator(name = "ID_ITI_DDS_SEQ", sequenceName = "ID_ITI_DDS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_ITI_DDS")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "TP_DS_ITI_DDS")
    private DiasDaSemana diaDaSemana;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_ITINERARIO", referencedColumnName = "ID_ITINERARIO", foreignKey = @ForeignKey(name = "FK_ITI_DDS_IT"))
    private Itinerario itinerario;

    public ItinerarioDiasDaSemana() {
    }

    public ItinerarioDiasDaSemana(DiasDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public ItinerarioDiasDaSemana(DiasDaSemana diaDaSemana, Itinerario itinerario) {
        this.diaDaSemana = diaDaSemana;
        this.itinerario = itinerario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiasDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiasDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }
}
