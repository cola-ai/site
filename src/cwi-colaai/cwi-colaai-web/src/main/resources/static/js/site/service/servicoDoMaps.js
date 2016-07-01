
/* global google */

"use strict";
var ServicoDeMaps = function ($mapa) {
    this.geocoder = new google.maps.Geocoder();
    this.directionsService = new google.maps.DirectionsService;
    this.directionsDisplay = new google.maps.DirectionsRenderer;
    this.directionsRoute = undefined;
    this.localizacaoAtual = undefined;
    this.definirMapaNaLocalizacaoAtual($mapa);
};

ServicoDeMaps.prototype = {
    novoMapa: function ($mapa) {
        return new google.maps.Map($mapa[0], {
            center: this.localizacaoAtual || { lat: -29.7549941, lng: -51.150283 },
            zoom: 10
        });        
    },

    getLocalizacaoAtual: function () {
        var self = this;

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (posicaoAtual) {
                self.localizacaoAtual = {
                    lat: posicaoAtual.coords.latitude,
                    lng: posicaoAtual.coords.longitude
                };
            }, function () {
                console.log("Erro ao buscar localização atual");
            });
        } else {
            console.log("Navegador não suporta Geolocation");
        }
    },
    
    definirMapaNaLocalizacaoAtual: function($mapa) {
        var self = this;
        
        this.getLocalizacaoAtual();
        setTimeout(function() {
            self.mapa = self.novoMapa($mapa);
            self.directionsDisplay.setMap(self.mapa);
        }, 500);
    },

    novoAlfinete: function (localizacao, query) {
        return new google.maps.Marker({
            map: this.mapa,
            place: {
                location: localizacao,
                query: query
            },
            attribution: {
                source: 'Google Maps JavaScript API',
                webUrl: 'https://developers.google.com/maps/'
            }
        });
    },

    novoInformativo: function (conteudo) {
        return new google.maps.InfoWindow({
            content: conteudo
        });
    },

    novaNota: function () {
        // TODO:
    },

    buscarLocalizacao: function (endereco) {
        return $.ajax({
            method: "GET",
            url: "https://maps.googleapis.com/maps/api/geocode/json",
            data: {
                address: endereco
            }
        });
    },
    
    buscarLocalizacaoPorId: function (id) {
        return $.ajax({
            method: "GET",
            url: "https://maps.googleapis.com/maps/api/geocode/json",
            data: {
                place_id: id
            }
        });
    },

    criarRota: function (origem, destino) {
        var self = this;
        
        this.directionsService.route({
            origin: new google.maps.LatLng(origem.lat, origem.lng),
            destination: new google.maps.LatLng(destino.lat, destino.lng),
            travelMode: google.maps.TravelMode.DRIVING
        }, function (response, status) {
            console.log(response);
            if (status === google.maps.DirectionsStatus.OK) {
                self.directionsDisplay.setDirections(response);
                self.directionsRoute = response.routes[0];
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    }
};