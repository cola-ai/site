"use strict";
(function () {
    window.App = window.App || {};

    App.RegistrarItinerarioView = {
        TEMPO_KEYPRESS_THREAD: 500,
        
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
            this.controller = new ItinerarioController(App.RegistrarItinerarioView);
        },
        
        buscarElementos: function () {
            this.$form = $("#registrar-itinerario-form");
            this.$mapa = $("#mapa");
            this.$btnBuscaLocais = this.$form.find("[busca-locais-btn]");
            this.$listaLocais = this.$form.find("[busca-locais-lista]");
            this.$inputBuscaLocais = this.$form.find("[busca-locais-input]");
            this.$btnBuscarRotas = this.$form.find("#buscar-rotas-btn");
        },
        
        atualizarElementos: function () {
            this.$form = $("#registrar-itinerario-form");
            this.$mapa = $("#mapa");
            
            this.$btnBuscaLocais = this.$form.find("[busca-locais-btn]");
            this.$listaLocais = this.$form.find("[busca-locais-lista]");
            this.$inputBuscaLocais = this.$form.find("[data-busca-locais]");
            
            this.$btnBuscarRotas = this.$form.find("#buscar-rotas-btn");
            
            this.$btnBuscarRotas.unbind("click");
            this.$inputBuscaLocais.unbind("keypress");
            this.$btnBuscaLocais.unbind("click");
            this.$listaLocais.find("[data-location]").unbind("click");
            
            this.vincularEventos();
        },
        
        vincularEventos: function () {
            var self = this;
            var $thread;

            this.$btnBuscarRotas.click(function () {
                var idOrigem = self.$listaLocais.find(">.active").data("location-id");
                var idDestino = self.$listaLocais.find(">.active").data("location-id");
                self.controller.criarRota(idOrigem, idDestino);
            });

            this.$inputBuscaLocais.on("keypress", function () {
                var $inputSelf = $(this);
                clearTimeout($thread);
                
                $thread = setTimeout(function () {
                    self.controller.buscarLocalizacao($inputSelf.val(), $inputSelf.data("busca-locais-lista"));
                }, self.TEMPO_KEYPRESS_THREAD);
            });

            this.$btnBuscaLocais.click(function () {
                var $input = $($(this).data("busca-locais-input"));
                self.controller.buscarLocalizacao($input.val(), $input.data("busca-locais-lista"));
            });

            this.$listaLocais.find("[data-location]").click(function (e) {
                self.controller.definirLocalizacao($(this).data("location"));
                self.$listaLocais.removeClass("active");
                $(this).addClass("active");
                
                return e.preventDefault();
            });
        },
        
        apresentarResultados: function(enderecos, idLista) {
            $(idLista).empty();
            
            $(idLista).append(
                enderecos.map(function(endereco) {
                    return $("<button>")
                                .attr("type", "button")
                                .addClass("list-group-item fade-in-left")
                                .attr("data-location", JSON.stringify(endereco.geometry.location))
                                .attr("data-location-id", endereco.place_id)
                                .text(endereco.formatted_address);
                })
            );
    
            this.atualizarElementos();            
        }
    };

    if ($("#mapa").size() > 0) {
        App.RegistrarItinerarioView.iniciar();
    }
})();