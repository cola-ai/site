"use strict";
(function () {
    window.App = window.App || {};

    App.ListarGruposDoUsuarioView = {
        controller: undefined,
        
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
            this.controller = new GrupoController(this);
            this.controller.listar();
        },
        
        buscarElementos: function () {
            this.$lista = $(".lista-de-grupos-home");
        },
        
        vincularEventos: function () {
            
        },
        
        preencherLista: function (grupos) {
            var self = this;
            this.$lista.append(grupos.map(function(grupo) {
                return $("<div>")
                        .addClass("media grupo")
                        .append(
                            $("<div>")
                            .addClass("media-body")
                            .append(
                                $("<h4>")
                                .addClass("media-heading")
                                .append(String.format("{0} - {1} {2}", grupo.nome, grupo.quantidadeVagas - grupo.participantes.length, App.Idioma.home.grupos_de_que_participo.lista.titulo))
                                .append(self.criarIconesDeVagasDoGrupo(grupo.quantidadeVagas, grupo.participantes.length))
                            )
                            .append(
                                $("<small>")
                                .addClass("media-sub-heading")
                                .text(String.format("{0} - {1} ({2})", grupo.itinerarios[0].origem.cidade, grupo.itinerarios[0].destino.cidade, grupo.itinerarios[0].horarioSaida))
                            )
                            .append(
                                grupo.participantes.map(function(usuario) {
                                    return $("<div>")
                                            .addClass("media usuario")
                                            .append(
                                                $("<div>")
                                                .addClass("media-left")
                                                .append($("<img>").addClass("media-object").attr("src", usuario.foto))
                                            )
                                            .append(
                                                $("<div>")
                                                .addClass("media-body")
                                                .append($("<h5>").addClass("media-heading").text(usuario.nome))
                                            );
                                })
                            )
                        );
            }));
        },
        
        criarIconesDeVagasDoGrupo: function(totalvagas, ocupadas) {
            var $icones = $("<span>").addClass("vagas");
            
            for(var i = 1; i <= totalvagas; i++) {
                $icones
                .append(
                    $("<i>")
                    .addClass("glyphicon glyphicon-modal-window")
                    .addClass(i <= ocupadas ? "ocupada" : "")
                );
            }
            
            return $icones;
        }        
    };
    
    if($(".lista-de-grupos-home").size() > 0) {
        App.ListarGruposDoUsuarioView.iniciar();
    }
})();