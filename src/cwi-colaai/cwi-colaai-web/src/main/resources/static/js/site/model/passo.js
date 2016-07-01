/*
 *  Passo
 */
var Passo = function(gStep) {
    this.duracao = gStep.duration.text;
    this.distancia = gStep.distance.text;
    this.pontoInicio = new Geolocalizacao(gStep.start_point.lat(), gStep.start_point.lng());
    this.localizacaoInicio = new Geolocalizacao(gStep.start_location.lat(), gStep.start_location.lng());
    this.pontoFim = new Geolocalizacao(gStep.end_point.lat(), gStep.end_point.lng());
    this.localizacaoFim = new Geolocalizacao(gStep.end_location.lat(), gStep.end_location.lng());
    this.latitudes_longitudes = this.getLatitudesLongitudes(gStep);
};

Passo.prototype = {
    getLatitudesLongitudes: function(gStep) {
        return gStep.lat_lngs.map(function(lat_lng) {
            return new Geolocalizacao(lat_lng.lat(), lat_lng.lng());
        });
    }
};