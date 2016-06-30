"use strict";
var ItinerarioController = function(view) {
    this.view = view;
    this.servico = new ServicoDeMaps(view.$mapa);
    this.mapaDoGoogle = null;
};

ItinerarioController.prototype = {
    buscarLocalizacao: function(endereco, idLista) {
        var self = this;
        if(!String.empty(endereco)) {
            this.servico.buscarLocalizacao(endereco)
                .done(function (data) {
                    self.view.apresentarResultados(data.results, idLista);
                });
        }
    },
    
    criarRota: function(idOrigem, idDestino) {
        this.servico.criarRota(idOrigem, idDestino);
    },
    
    definirLocalizacao: function(local) {
        
    }
};