
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.DiasDaSemana;
import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class BasicoItinerarioViewModel {
    
    private Long itinerarioId;
    private LocalViewModel origem;
    private LocalViewModel destino;
    private String horarioSaida;
    private List<DiasDaSemana> diasDaSemana;

    public BasicoItinerarioViewModel() {
    }

    public BasicoItinerarioViewModel(Long itinerarioId, LocalViewModel origem, LocalViewModel destino, String horarioSaida, List<DiasDaSemana> diasDaSemana) {
        this.itinerarioId = itinerarioId;
        this.origem = origem;
        this.destino = destino;
        this.horarioSaida = horarioSaida;
        this.diasDaSemana = diasDaSemana;
    }

    public Long getItinerarioId() {
        return itinerarioId;
    }

    public void setItinerarioId(Long itinerarioId) {
        this.itinerarioId = itinerarioId;
    }

    public LocalViewModel getOrigem() {
        return origem;
    }

    public void setOrigem(LocalViewModel origem) {
        this.origem = origem;
    }

    public LocalViewModel getDestino() {
        return destino;
    }

    public void setDestino(LocalViewModel destino) {
        this.destino = destino;
    }

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public List<DiasDaSemana> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(List<DiasDaSemana> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }
}
