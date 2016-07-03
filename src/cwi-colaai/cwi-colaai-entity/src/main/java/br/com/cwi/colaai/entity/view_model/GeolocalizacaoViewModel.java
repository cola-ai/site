
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.Geolocalizacao;

/**
 *
 * @author Érico de Souza Loewe
 */
public class GeolocalizacaoViewModel {
    
    private Double latitude;
    private Double longitude;

    public GeolocalizacaoViewModel() {
    }

    public GeolocalizacaoViewModel(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public Geolocalizacao toGeolocalizacao() {
        return new Geolocalizacao(latitude, longitude);
    }
}
