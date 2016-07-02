
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.Geolocalizacao;
import br.com.cwi.colaai.entity.PassoDeRota;
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
    private GeolocalizacaoVieModel pontoInicio;
    private GeolocalizacaoVieModel localizacaoInicio;
    private GeolocalizacaoVieModel pontoFim;
    private GeolocalizacaoVieModel localizacaoFim;
    private List<GeolocalizacaoVieModel> latitudes_longitudes;

    public PassoDeRotaViewModel() {
    }

    public PassoDeRotaViewModel(String duracao, String distancia, GeolocalizacaoVieModel pontoInicio, GeolocalizacaoVieModel localizacaoInicio, GeolocalizacaoVieModel pontoFim, GeolocalizacaoVieModel localizacaoFim, List<GeolocalizacaoVieModel> latitudes_longitudes) {
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

    public GeolocalizacaoVieModel getPontoInicio() {
        return pontoInicio;
    }

    public void setPontoInicio(GeolocalizacaoVieModel pontoInicio) {
        this.pontoInicio = pontoInicio;
    }

    public GeolocalizacaoVieModel getLocalizacaoInicio() {
        return localizacaoInicio;
    }

    public void setLocalizacaoInicio(GeolocalizacaoVieModel localizacaoInicio) {
        this.localizacaoInicio = localizacaoInicio;
    }

    public GeolocalizacaoVieModel getPontoFim() {
        return pontoFim;
    }

    public void setPontoFim(GeolocalizacaoVieModel pontoFim) {
        this.pontoFim = pontoFim;
    }

    public GeolocalizacaoVieModel getLocalizacaoFim() {
        return localizacaoFim;
    }

    public void setLocalizacaoFim(GeolocalizacaoVieModel localizacaoFim) {
        this.localizacaoFim = localizacaoFim;
    }

    public List<GeolocalizacaoVieModel> getLatitudes_longitudes() {
        return latitudes_longitudes;
    }

    public void setLatitudes_longitudes(List<GeolocalizacaoVieModel> latitudes_longitudes) {
        this.latitudes_longitudes = latitudes_longitudes;
    }

    public PassoDeRota toPassoDeRota() {
        ArrayList<Trajeto> trajetoria = new ArrayList<>();
        latitudes_longitudes.forEach((t) -> {trajetoria.add(new Trajeto(t.toGeolocalizacao()));});
        return new PassoDeRota(duracao, distancia, pontoInicio.toGeolocalizacao(), localizacaoInicio.toGeolocalizacao(), pontoFim.toGeolocalizacao(), localizacaoFim.toGeolocalizacao(), trajetoria);
    }
}
