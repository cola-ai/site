
package br.com.cwi.colaai.entity.view_model;

/**
 *
 * @author Érico de Souza Loewe
 */
class PassoDeRotaViewModel {
    
    private String duracao;
    private String distancia;
    private GeolocalizacaoVieModel pontoInicio;
    private GeolocalizacaoVieModel localizacaoInicio;
    private GeolocalizacaoVieModel pontoFim;
    private GeolocalizacaoVieModel localizacaoFim;

    public PassoDeRotaViewModel() {
    }

    public PassoDeRotaViewModel(String duracao, String distancia, GeolocalizacaoVieModel pontoInicio, GeolocalizacaoVieModel localizacaoInicio, GeolocalizacaoVieModel pontoFim, GeolocalizacaoVieModel localizacaoFim) {
        this.duracao = duracao;
        this.distancia = distancia;
        this.pontoInicio = pontoInicio;
        this.localizacaoInicio = localizacaoInicio;
        this.pontoFim = pontoFim;
        this.localizacaoFim = localizacaoFim;
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
}
