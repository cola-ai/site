
package br.com.cwi.colaai.entity;

import br.com.cwi.colaai.entity.view_model.BasicoItinerarioViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@Table(name = "ITINERARIO", indexes = {
    @Index(columnList = "ID_ROTA", name = "IX_ITINERARIO_RT"),
    @Index(columnList = "ID_USUARIO", name = "IX_ITINERARIO_US"),
    @Index(columnList = "ID_LOCAL_ITINERARIO_OR", name = "IX_ITINERARIO_OR"),
    @Index(columnList = "ID_LOCAL_ITINERARIO_DT", name = "IX_ITINERARIO_DT"),
    @Index(columnList = "HR_SAIDA_ITINERARIO", name = "IX_ITINERARIO_HR"),
})
public class Itinerario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITINERARIO")
    @SequenceGenerator(name = "SEQ_ITINERARIO", sequenceName = "SEQ_ITINERARIO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_ITINERARIO")
    private Long id;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_LOCAL_ITINERARIO_OR", referencedColumnName = "ID_LOCAL_ITINERARIO", foreignKey = @ForeignKey(name = "FK_ITINERARIO_LOCAL_OR"))
    private Local origem;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_LOCAL_ITINERARIO_DT", referencedColumnName = "ID_LOCAL_ITINERARIO", foreignKey = @ForeignKey(name = "FK_ITINERARIO_LOCAL_DT"))
    private Local destino;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ROTA", referencedColumnName = "ID_ROTA", foreignKey = @ForeignKey(name = "FK_ITINERARIO_ROTA_RT"))
    private Rota rota;
    
    @Basic(optional = false)
    @Column(name = "HR_SAIDA_ITINERARIO")
    private String horarioSaida;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_ITINERARIO_USUARIO_US"))
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO", foreignKey = @ForeignKey(name = "FK_ITINERARIO_GRUPO_GP"))
    private Grupo grupo;
    
    @OneToMany(mappedBy="itinerario",cascade = CascadeType.ALL)
    private List<ItinerarioDiasDaSemana> diasDaSemana;

    public Itinerario() {
    }

    public Itinerario(Local origem, Local destino, Rota rota, String horarioSaida, Usuario usuario) {
        this.origem = origem;
        this.destino = destino;
        this.rota = rota;
        this.horarioSaida = horarioSaida;
        this.usuario = usuario;
    }

    public Itinerario(Local origem, Local destino, String horarioSaida, List<ItinerarioDiasDaSemana> diasDaSemana, Rota rota) {
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
    
    public List<DiasDaSemana> getEnumDiasDaSemana() {
        ArrayList<DiasDaSemana> diasDaSemana = new ArrayList<>();
        
        this.diasDaSemana.forEach((d) -> {
            diasDaSemana.add(d.getDiaDaSemana());
        });
        
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

    public BasicoItinerarioViewModel toBasicoViewModel() {
        ArrayList<DiasDaSemana> diasDaSemana = new ArrayList<>();
        
        this.diasDaSemana.forEach((d) -> {
            diasDaSemana.add(d.getDiaDaSemana());
        });
        
        return new BasicoItinerarioViewModel(id, origem.toViewModel(), destino.toViewModel(), horarioSaida, diasDaSemana);
    }
}
