
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.DiasDaSemana;
import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.ItinerarioDiasDaSemana;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class ItinerarioViewModel {
    
    private Long itinerarioId;
    private LocalViewModel origem;
    private LocalViewModel destino;
    private String horarioSaida;
    private List<DiasDaSemana> diasDaSemana;
    private RotaViewModel rota;

    public ItinerarioViewModel() {
    }

    public ItinerarioViewModel(Long itinerarioId, LocalViewModel origem, LocalViewModel destino, String horarioSaida, List<DiasDaSemana> diasDaSemana, RotaViewModel rota) {
        this.itinerarioId = itinerarioId;
        this.origem = origem;
        this.destino = destino;
        this.horarioSaida = horarioSaida;
        this.diasDaSemana = diasDaSemana;
        this.rota = rota;
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

    public RotaViewModel getRota() {
        return rota;
    }

    public void setRota(RotaViewModel rota) {
        this.rota = rota;
    }

    public Itinerario toItinerario() {
        ArrayList<ItinerarioDiasDaSemana> itiDiasDasemana = new ArrayList<>();
        diasDaSemana.forEach((d) -> {itiDiasDasemana.add(new ItinerarioDiasDaSemana(d));});
        return new Itinerario(origem.toLocal(), destino.toLocal(), horarioSaida, itiDiasDasemana, rota.toRota());
    }
}
