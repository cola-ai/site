
package br.com.cwi.colaai.entity.view_model;

import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class RotaViewModel {
    
    private String duracao;
//    private String distancia;
    private String enderecoInicio;
    private GeolocalizacaoVieModel localizacaoInicio;
    private String enderecoFim;
    private GeolocalizacaoVieModel localizacaoFim;
    private List<PassoDeRotaViewModel> passos;

    public RotaViewModel() {
    }

    public RotaViewModel(String duracao, String distancia, String enderecoInicio, GeolocalizacaoVieModel localizacaoInicio, String enderecoFim, GeolocalizacaoVieModel localizacaoFim, List<PassoDeRotaViewModel> passos) {
        this.duracao = duracao;
//        this.distancia = distancia;
        this.enderecoInicio = enderecoInicio;
        this.localizacaoInicio = localizacaoInicio;
        this.enderecoFim = enderecoFim;
        this.localizacaoFim = localizacaoFim;
        this.passos = passos;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

//    public String getDistancia() {
//        return distancia;
//    }
//
//    public void setDistancia(String distancia) {
//        this.distancia = distancia;
//    }

    public String getEnderecoInicio() {
        return enderecoInicio;
    }

    public void setEnderecoInicio(String enderecoInicio) {
        this.enderecoInicio = enderecoInicio;
    }

    public GeolocalizacaoVieModel getLocalizacaoInicio() {
        return localizacaoInicio;
    }

    public void setLocalizacaoInicio(GeolocalizacaoVieModel localizacaoInicio) {
        this.localizacaoInicio = localizacaoInicio;
    }

    public String getEnderecoFim() {
        return enderecoFim;
    }

    public void setEnderecoFim(String enderecoFim) {
        this.enderecoFim = enderecoFim;
    }

    public GeolocalizacaoVieModel getLocalizacaoFim() {
        return localizacaoFim;
    }

    public void setLocalizacaoFim(GeolocalizacaoVieModel localizacaoFim) {
        this.localizacaoFim = localizacaoFim;
    }

    public List<PassoDeRotaViewModel> getPassos() {
        return passos;
    }

    public void setPassos(List<PassoDeRotaViewModel> passos) {
        this.passos = passos;
    }
}
