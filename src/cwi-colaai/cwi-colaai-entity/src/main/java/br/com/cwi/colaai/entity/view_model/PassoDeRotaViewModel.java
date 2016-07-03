
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.Geolocalizacao;
import br.com.cwi.colaai.entity.PassoDeRota;
import br.com.cwi.colaai.entity.Rota;
import br.com.cwi.colaai.entity.Trajeto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class PassoDeRotaViewModel {
    
    private String duracao;
    private String distancia;
    private GeolocalizacaoViewModel pontoInicio;
    private GeolocalizacaoViewModel localizacaoInicio;
    private GeolocalizacaoViewModel pontoFim;
    private GeolocalizacaoViewModel localizacaoFim;
    private List<GeolocalizacaoViewModel> latitudes_longitudes;

    public PassoDeRotaViewModel() {
    }

    public PassoDeRotaViewModel(String duracao, String distancia, GeolocalizacaoViewModel pontoInicio, GeolocalizacaoViewModel localizacaoInicio, GeolocalizacaoViewModel pontoFim, GeolocalizacaoViewModel localizacaoFim, List<GeolocalizacaoViewModel> latitudes_longitudes) {
        this.duracao = duracao;
        this.distancia = distancia;
        this.pontoInicio = pontoInicio;
        this.localizacaoInicio = localizacaoInicio;
        this.pontoFim = pontoFim;
        this.localizacaoFim = localizacaoFim;
        this.latitudes_longitudes = latitudes_longitudes;
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

    public GeolocalizacaoViewModel getPontoInicio() {
        return pontoInicio;
    }

    public void setPontoInicio(GeolocalizacaoViewModel pontoInicio) {
        this.pontoInicio = pontoInicio;
    }

    public GeolocalizacaoViewModel getLocalizacaoInicio() {
        return localizacaoInicio;
    }

    public void setLocalizacaoInicio(GeolocalizacaoViewModel localizacaoInicio) {
        this.localizacaoInicio = localizacaoInicio;
    }

    public GeolocalizacaoViewModel getPontoFim() {
        return pontoFim;
    }

    public void setPontoFim(GeolocalizacaoViewModel pontoFim) {
        this.pontoFim = pontoFim;
    }

    public GeolocalizacaoViewModel getLocalizacaoFim() {
        return localizacaoFim;
    }

    public void setLocalizacaoFim(GeolocalizacaoViewModel localizacaoFim) {
        this.localizacaoFim = localizacaoFim;
    }

    public List<GeolocalizacaoViewModel> getLatitudes_longitudes() {
        return latitudes_longitudes;
    }

    public void setLatitudes_longitudes(List<GeolocalizacaoViewModel> latitudes_longitudes) {
        this.latitudes_longitudes = latitudes_longitudes;
    }

    public PassoDeRota toPassoDeRota() {
        ArrayList<Trajeto> trajetoria = new ArrayList<>();
        latitudes_longitudes.forEach((t) -> {trajetoria.add(new Trajeto(t.toGeolocalizacao()));});
        return new PassoDeRota(duracao, distancia, pontoInicio.toGeolocalizacao(), localizacaoInicio.toGeolocalizacao(), pontoFim.toGeolocalizacao(), localizacaoFim.toGeolocalizacao(), trajetoria);
    }

    public PassoDeRota toPassoDeRota(Rota rota) {
        return new PassoDeRota(duracao, distancia, pontoInicio.toGeolocalizacao(), localizacaoInicio.toGeolocalizacao(), pontoFim.toGeolocalizacao(), localizacaoFim.toGeolocalizacao(), rota);
    }
}
