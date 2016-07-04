"use strict";
(function () {
    window.App = window.App || {};

    App.PesquisarGruposView = {
        tiposDeBusca: new Array("NOME", "ORIGEM", "DESTINO", "HORARIO"),
        controller: undefined,
        
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
            this.preencherSelects();
            this.controller = new GrupoController(this);
        },
        
        buscarElementos: function () {
            this.$form = $("#pesquisar-grupo-form");
            this.$lista = $(".lista-de-grupos-pesquisar");
            this.$btnNovoFiltro = $(".btn-novo-filtro");
        },
        
        atualizarElementos: function () {
            this.$form = $("#pesquisar-grupo-form");
            this.$lista = $(".lista-de-grupos-pesquisar");
            
            this.$lista.find("[ver-mais]").unbind("click");
            this.$btnNovoFiltro.unbind("click");
            this.$form.unbind("submit");
            
            this.preencherSelects();
            this.vincularEventos();
        },
        
        vincularEventos: function () {
            var self = this;
            
            this.$btnNovoFiltro.click(function(e) {
                self.criarNovoFiltro();
                
                return e.preventDefault();
            });
            
            this.$form.submit(function (e) {
                e.preventDefault();
                self.controller.pesquisar(self.filtrarItemsParaBusca());
                return e.preventDefault();
            });
            
            this.$lista.find("[ver-mais]").click(function() {
                
            });
        },
        
        filtrarItemsParaBusca: function () {
            var filtro = {};
            
            this.$form.find(".form-group").each(function(i, group) {
                var $group = $(group);
                
                if($group.find(".select-pesquisa").size() > 0) {
                    filtro[$group.find(".select-pesquisa").val().toLowerCase()] = $group.find(".input-pesquisa").val();
                }
            });
            
            return filtro;
        },
        
        preencherSelects: function () {
            this.$form.find(".select-pesquisa")
                    .html(
                        this.tiposDeBusca
                        .map(function(tbusca) {
                            return $("<option>").attr("val", tbusca).text(tbusca);
                        })
                    );
        },
        
        preencherLista: function (grupos) {
            var self = this;
            this.$lista.empty();
            
            this.$lista.append(
            grupos.map(function(grupo) {
                return $("<div>")
                        .addClass("grupo media default-background")
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
                                $("<div>").addClass("media-list usuarios-do-grupo hidden").append(
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
                                }))
                            )
                        ).append(
                            $("<div>")
                            .addClass("media-right")
                            .append(
                                $("<button>")
                                .attr("data-adicionar-grupo", grupo.id)
                                .addClass("btn btn-default")
                                .append($("<i>").addClass("glyphicon glyphicon-send"))
                                .append("&nbsp;&nbsp;Enviar Solicitação")
                            )
                            .append(
                                $("<button>")
                                .attr("ver-mais", "")
                                .addClass("btn btn-default")
                                .append($("<i>").addClass("glyphicon glyphicon-zoom-in"))
                                .append("&nbsp;&nbsp;Ver Mais")
                            )
                        );
            }));
            
            if(grupos.length === 0) {
                this.$lista.append(
                    $("<div>")
                     .addClass("alert alert-info default-shadow")
                     .append("Nenhum grupo foi encontrado com cristerios de busca")
                );
            }
        },
        
        criarNovoFiltro: function () {
            if(this.$form.find(".form-group").size() <= this.tiposDeBusca.length) {
                this.$form.prepend(this.criarFormGroup());
                this.atualizarElementos();
            }
        },
        
        criarFormGroup: function () {
            return $("<div>")
                    .addClass("form-group")
                    .append(
                        $("<div>")
                        .addClass("row")
                        .append(
                            $("<div>")
                            .addClass("col-sm-10")
                            .append(
                                $("<input>")
                                .attr("type", "text")
                                .addClass("form-control input-pesquisa")
                            )
                        )
                        .append(
                            $("<div>")
                            .addClass("col-sm-2")
                            .append(
                                $("<select>")
                                .addClass("form-control select-pesquisa")
                            )
                        )
                    );
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
    App.PesquisarGruposView.iniciar();
})();