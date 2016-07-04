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
        if(this.estaCompleto(itinerario)) {
            itinerario.rota = this.prepararRota();
            itinerario.origem = new Local(this.informacoesRota.origem);
            itinerario.destino = new Local(this.informacoesRota.destino);            
            debugger;
            this.itinerario
                    .registrar(itinerario)
                    .done(function (data) {
                        ColaAi.Modal.sucesso(ColaAi.Idioma.itinerario.registrar.mensagem.sucesso);
                    }).fail(function(data) {
                        ColaAi.Modal.erro(ColaAi.Idioma.itinerario.registrar.mensagem.erro);
                    });
        }
    },
    
    prepararRota: function () {
        var passos = this.servico.directionsRoute.legs[0];
        
        return {
            duracao: passos.duration.text,
            distancia: passos.distance.text,
            polilyne: this.servico.directionsRoute.overview_polyline,
            passos: passos.steps.map(function(gStep){
                return new Passo(gStep);
            })
        };
    },
    
    estaCompleto: function (itinerario) {
        var estaCompleto = true;
        
        $("#registrar-itinerario-form").find(".alert.alert-danger").remove();
        
        if(itinerario.horarioSaida === "") {
            estaCompleto = false;
            ColaAi.Mensagem.erro(ColaAi.Idioma.itinerario.registrar.validacao.horarioSaida, "#registrar-itinerario-form");
        }
        
        if (itinerario.diasDaSemana === null) {
            estaCompleto = false;
            ColaAi.Mensagem.erro(ColaAi.Idioma.itinerario.registrar.validacao.diasDaSemana, "#registrar-itinerario-form");
        } 
        
        if (this.informacoesRota.origem === undefined) {
            estaCompleto = false;
            ColaAi.Mensagem.erro(ColaAi.Idioma.itinerario.registrar.validacao.origem, "#registrar-itinerario-form");
        }
        
        if (this.informacoesRota.destino === undefined) {
            estaCompleto = false;
            ColaAi.Mensagem.erro(ColaAi.Idioma.itinerario.registrar.validacao.destino, "#registrar-itinerario-form");
        }
        
        if (this.servico.directionsRoute === undefined) {
            estaCompleto = false;
            ColaAi.Mensagem.erro(ColaAi.Idioma.itinerario.registrar.validacao.rota, "#registrar-itinerario-form");
        }
        
        return estaCompleto;
    }
};