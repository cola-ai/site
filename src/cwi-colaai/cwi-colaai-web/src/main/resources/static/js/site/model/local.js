/*
 *  Local
 */
var Local = function(gPlace) {    
    this.pais = this.getPais(gPlace);
    this.estado = this.getEstado(gPlace);
    this.cidade = this.getCidade(gPlace);
    this.cep = this.getCep(gPlace);
    this.bairro = this.getBairro(gPlace);
    this.rua = this.getRua(gPlace);
    this.numero = this.getNumero(gPlace);
    this.localizacao = new Geolocalizacao(gPlace.geometry.location.lat, gPlace.geometry.location.lng);
};

Local.prototype = {
    getPais: function(gPlace) {
        var pais = gPlace
                        .address_components
                        .filter(function(ponto) {
                            return (ponto.types.filter(function(t){ return t === "country"; }).length > 0);
                        });
        return pais.length > 0 ? pais[0].long_name : undefined;
    },
    
    getEstado: function(gPlace) {
        var estado = gPlace
                        .address_components
                        .filter(function(ponto) {
                            return (ponto.types.filter(function(t){ return t === "administrative_area_level_1"; }).length > 0);
                        });
        return estado.length > 0 ? estado[0].long_name : undefined;
    },
    
    getCidade: function(gPlace) {
        var cidade = gPlace
                        .address_components
                        .filter(function(ponto) {
                            return (ponto.types.filter(function(t){ return t === "locality" || t === "administrative_area_level_2"; }).length > 0);
                        });
        return cidade.length > 0 ? cidade[0].long_name : undefined;
    },
    
    getBairro: function(gPlace) {
        var bairro = gPlace
                        .address_components
                        .filter(function(ponto) {
                            return (ponto.types.filter(function(t){ return t === "sublocality" || t === "sublocality_level_1"; }).length > 0);
                        });
        return bairro.length > 0 ? bairro[0].long_name : undefined;
    },
    
    getRua: function(gPlace) {
        var rua = gPlace
                    .address_components
                    .filter(function(ponto) {
                        return (ponto.types.filter(function(t){ return t === "route"; }).length > 0);
                    });
        return rua.length > 0 ? rua[0].long_name : undefined;
    },
    
    getNumero: function(gPlace) {
        var numero = gPlace
                        .address_components
                        .filter(function(ponto) {
                            return (ponto.types.filter(function(t){ return t === "street_number"; }).length > 0);
                        });
        return numero.length > 0 ? numero[0].long_name : undefined;
    },
    
    getCep: function(gPlace) {
        var cep = gPlace
                    .address_components
                    .filter(function(ponto) {
                        return (ponto.types.filter(function(t){ return t === "postal_code"; }).length > 0);
                    });
        return cep.length > 0 ? cep[0].long_name : undefined;
    }
};