
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.Geolocalizacao;

/**
 *
 * @author Érico de Souza Loewe
 */
public class GeolocalizacaoVieModel {
    
    private Double latitude;
    private Double longitude;

    public GeolocalizacaoVieModel() {
    }

    public GeolocalizacaoVieModel(Double latitude, Double longitude) {
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
