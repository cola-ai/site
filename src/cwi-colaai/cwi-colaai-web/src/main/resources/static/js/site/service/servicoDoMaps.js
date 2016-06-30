/* global google */

"use strict";

var ServicoDeMaps = function ($mapa) {
    this.mapa = this.novoMapa($mapa);
    this.geocoder = new google.maps.Geocoder();
    this.directionsService = new google.maps.DirectionsService;
    this.directionsDisplay = new google.maps.DirectionsRenderer;
    this.directionsDisplay.setMap(this.mapa);
};

ServicoDeMaps.prototype = {
    novoMapa: function ($mapa) {
        return new google.maps.Map($mapa[0], {
            center: { lat: -29.7549941, lng: -51.150283 },
            zoom: 10
        });        
    },

    localizacaoAtual: function () {
        var localizacao = null;

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (posicaoAtual) {
                localizacao = {
                    lat: posicaoAtual.coords.latitude,
                    lng: posicaoAtual.coords.longitude
                };
                console.log(localizacao);
            }, function () {
                alert("Erro ao buscar localização atual");
            });
        } else {
            alert("Navegador não suporta Geolocation");
        }

        return localizacao;
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

    criarRota: function (origem, destino) {
        var self = this;
        
        this.directionsService.route({
            origin: new google.maps.LatLng(origem.lat, origem.lng),
            destination: new google.maps.LatLng(origem.lat, origem.lng),
            travelMode: google.maps.TravelMode.DRIVING
        }, function (response, status) {
            if (status === google.maps.DirectionsStatus.OK) {
                self.directionsDisplay.setDirections(response);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    }
};