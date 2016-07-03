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
        
            this.itinerario
                    .registrar(itinerario)
                    .done(function (data) {
                        App.Modal.sucesso("Itinerario criado com sucesso.");
                    }).fail(function(data) {
                        App.Modal.erro("Problemas ao salvar itinerario.");
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
        
        if(itinerario.horarioSaida === "") {
            estaCompleto = false;
            App.Mensagem.erro("Preencha o horario de saida.", "#registrar-itinerario-form");
        }
        
        if (itinerario.diasDaSemana === null) {
            estaCompleto = false;
            App.Mensagem.erro("Selecione ao menos um dia da semana.", "#registrar-itinerario-form");
        } 
        
        if (this.informacoesRota.origem === undefined) {
            estaCompleto = false;
            App.Mensagem.erro("Selecione ao menos uma origem.", "#registrar-itinerario-form");
        }
        
        if (this.informacoesRota.destino === undefined) {
            estaCompleto = false;
            App.Mensagem.erro("Selecione ao menos um destino", "#registrar-itinerario-form");
        }
        
        if (this.servico.directionsRoute === undefined) {
            estaCompleto = false;
            App.Mensagem.erro("Você deve gerar uma rota para prosseguir com o cadastro!", "#registrar-itinerario-form");
        }
        
        return estaCompleto;
    }
};