"use strict";
var ItinerarioController = function (view) {
    this.view = view;
    this.servico = new ServicoDeMaps(view.$mapa);
    this.itinerario = new Itinerario();
    this.informacoesRota = {
        origem: undefined,
        destino: undefined
    };
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
    
    criarRota: function (origem, destino) {
        var self = this;
        this.servico.criarRota(origem, destino);
        this.servico.buscarLocalizacao(origem.lugar)
                    .done(function (data) {
                        self.informacoesRota.origem = data.results[0];
                    });
        this.servico.buscarLocalizacao(destino.lugar)
                    .done(function (data) {
                        self.informacoesRota.destino = data.results[0];
                    });
    },
    
    definirLocalizacao: function (local) {

    },
    
    registrar: function (itinerario) {
        itinerario.rota = this.prepararRota();
        itinerario.origem = new Local(this.informacoesRota.origem);
        itinerario.destino = new Local(this.informacoesRota.destino);
        
        this.itinerario
                .registrar(itinerario)
                .done(function (data) {
                    console.log(data);
                });
    },
    
    prepararRota: function () {
        var passos = this.servico.directionsRoute.legs[0];
        
        return {
            duracao: passos.duration.text,
            distancia: passos.distance.text,
            passos: passos.steps.map(function(gStep){
                return new Passo(gStep);
            })
        };
    }
};