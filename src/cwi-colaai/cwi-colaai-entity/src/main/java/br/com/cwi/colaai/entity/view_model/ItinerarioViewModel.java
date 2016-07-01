
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.DiasDaSemana;
import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class ItinerarioViewModel {
    
    private Long itinerarioId;
    private String origem;
    private String destino;
    private String horarioSaida;
//    private List<DiasDaSemana> diasDaSemana;
    private RotaViewModel rota;

    public ItinerarioViewModel() {
    }

    public ItinerarioViewModel(Long itinerarioId, String origem, String destino, String horarioSaida, List<DiasDaSemana> diasDaSemana, RotaViewModel rota) {
        this.itinerarioId = itinerarioId;
        this.origem = origem;
        this.destino = destino;
        this.horarioSaida = horarioSaida;
//        this.diasDaSemana = diasDaSemana;
        this.rota = rota;
    }

    public Long getItinerarioId() {
        return itinerarioId;
    }

    public void setItinerarioId(Long itinerarioId) {
        this.itinerarioId = itinerarioId;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

//    public List<DiasDaSemana> getDiasDaSemana() {
//        return diasDaSemana;
//    }
//
//    public void setDiasDaSemana(List<DiasDaSemana> diasDaSemana) {
//        this.diasDaSemana = diasDaSemana;
//    }

    public RotaViewModel getRota() {
        return rota;
    }

    public void setRota(RotaViewModel rota) {
        this.rota = rota;
    }
}
