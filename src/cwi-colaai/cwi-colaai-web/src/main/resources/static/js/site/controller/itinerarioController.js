"use strict";
var ItinerarioController = function (view) {
    this.view = view;
    this.servico = new ServicoDeMaps(view.$mapa);
    this.itinerario = new Itinerario();
};

ItinerarioController.prototype = {
    buscarLocalizacao: function (endereco, idLista) {
        var self = this;
        if (!String.empty(endereco)) {
            this.servico.buscarLocalizacao(endereco)
                    .done(function (data) {
                        self.view.apresentarResultados(data.results, idLista);
                    });
        }
    },
    
    criarRota: function (idOrigem, idDestino) {
        this.servico.criarRota(idOrigem, idDestino);
    },
    
    definirLocalizacao: function (local) {

    },
    
    registrar: function (itinerario) {
        itinerario.rota = this.prepararRota();
        this.itinerario
                .registrar(itinerario)
                .done(function (data) {

                });
    },
    
    prepararRota: function () {
        var passos = this.servico.directionsRoute.legs[0];
        
        return {
            duracao: passos.duration.text,
            distancia: passos.distance.text,
            enderecoInicio: passos.start_address,
            localizacaoInicio: {latitude: passos.end_location.lat(), longitude: passos.end_location.lng()},
            enderecoFim: passos.end_address,
            localizacaoFim: {latitude: passos.end_location.lat(), longitude: passos.end_location.lng()},
            passos: passos.steps.map(function(passo){
                return {
                    duracao: passo.distance.text,
                    distancia: passo.duration.text,
                    pontoInicio: {latitude: passo.start_point.lat(), longitude: passo.start_point.lng()},
                    localizacaoInicio: {latitude: passo.start_location.lat(), longitude: passo.start_location.lng()},
                    pontoFim: {latitude: passo.end_point.lat(), longitude: passo.end_point.lng()},
                    localizacaoFim: {latitude: passo.end_location.lat(), longitude: passo.end_location.lng()}
                };
            })
        };
        return this.servico
                    .directionsRoute;
    }
};