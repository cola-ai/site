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
            this.$form.find("#horario-saida").unbind("keypress");
            this.$form.unbind("submit");
            
            this.vincularEventos();
        }, 
        
        vincularEventos: function () {
            var self = this;
            var $thread;
            
            App.Mascara.hora(this.$form.find("#horario-saida"));
            
            this.$form.submit(function(e) {
                e.preventDefault();
                self.controller.registrar({
                   origem: $(this).find("#origem").val(),
                   destino: $(this).find("#destino").val(),
                   horarioSaida: $(this).find("#horario-saida").val(),
                   diasDaSemana: $(this).find("#dias-da-semana").val()
                });
                return e.preventDefault();
            });
            
            this.$form.find("#dias-da-semana").select2({
                placeholder: App.Idioma.itinerario.registrar.dias_da_semana.placeholder
            });

            this.$btnBuscarRotas.click(function () {
                var origem = self.$listaLocais.filter("#origem-lista").find(">.active").data("location");
                var destino = self.$listaLocais.filter("#destino-lista").find(">.active").data("location");
                self.controller.criarRota(origem, destino);
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