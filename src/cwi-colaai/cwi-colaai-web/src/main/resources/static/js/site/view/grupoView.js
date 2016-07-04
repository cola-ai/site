"use strict";
(function () {
    window.App = window.App || {};

    App.NovasSolicitacoes = {
        iniciar: function() {
            this.controller = new GrupoController(this);
            this.buscarElementos();
            this.controller.atualizarSolicitacoes();
        },
        
        buscarElementos: function() {
            this.$btnNavbar = $(".solicitacoes-btn");
            this.$dropdown = $(".solicitacoes-dropdown");
            this.$btnRecusar = $("[data-solicitacao-rescusar]");
            this.$btnAceitar = $("[data-solicitacao-aceitar]");
        },
        
        atualizarElementos: function() {
            this.$btnNavbar = $(".solicitacoes-btn");
            this.$dropdown = $(".solicitacoes-dropdown");
            this.$btnRecusar = $("[data-solicitacao-rescusar]");
            this.$btnAceitar = $("[data-solicitacao-aceitar]");
            this.$btnAceitar.unbind("click");
            this.$btnRecusar.unbind("click");
            this.$btnNavbar.unbind("click");
            this.vincularEventos();
        },
        
        vincularEventos: function() {
            var self = this;
            
            this.$btnNavbar.click(function(e) {
                $(this).parent().toggleClass("open");
                return e.preventDefault();
            });
            
            this.$btnAceitar.click(function(e) {
                self.controller.aceitarSolicitacao({idSolicitacao: $(this).data("solicitacao-aceitar")});
                self.fecharSolicitacao($(this));
                return e.preventDefault();
            });
            
            this.$btnRecusar.click(function(e) {
                self.controller.recusarSolicitacao({idSolicitacao: $(this).data("solicitacao-rescusar")});
                self.fecharSolicitacao($(this));
                return e.preventDefault();
            });
        },
        
        fecharSolicitacao: function($btnSolicitacao) {
            var self = this;
            $btnSolicitacao.closest(".media").addClass("sair-e-fechar");
                
            setTimeout(function() {
                $btnSolicitacao.closest(".media").remove();
                if(self.$dropdown.find("li").length === 0) {
                    self.zerarSolicitacoes();
                } else {
                    self.atualizarBtnInfo();
                }
            }, 1010);
        },
        
        getpopoverTemplate: function() {
            return this.$dropdown;
        },
        
        apresentarSolicitacoes: function(solicitacoes) {
            this.preencherDropdown(solicitacoes);
            this.atualizarBtnInfo(solicitacoes.length);
        },
        
        atualizarBtnInfo: function(quantidadeDeSolicitacoes) {
            if(quantidadeDeSolicitacoes === undefined) {
                this.$btnNavbar.find(".badge-notify").text(this.$btnNavbar.find(".badge-notify").text() - 1);
            } else if(quantidadeDeSolicitacoes > 0) {
                this.$btnNavbar.find(".badge-notify").text(quantidadeDeSolicitacoes);
                this.$btnNavbar.find(".badge-notify").removeClass("hidden");
            } else {
                this.$btnNavbar.find(".badge-notify").addClass("hidden");
                this.$btnNavbar.find(".badge-notify").text("");
            }
        },
        
        preencherDropdown: function(solicitacoes) {
            var self = this;
            
            this.$dropdown.empty();
            
            this.$dropdown.append(
                solicitacoes.map(function(solicitacao) {                
                    return $("<li>")
                            .addClass("media")
                            .append(
                                $("<div>")
                                .addClass("media-left")
                                .append(
                                    $("<img>")
                                    .addClass("media-object")
                                    .attr("src", solicitacao.usuario.foto)
                                )
                            )
                            .append(
                                $("<div>")
                                .addClass("media-body")
                                .append(
                                    $("<h6>")
                                    .text(String.format("{0} deseja entrar no grupo \"{1}\"", solicitacao.usuario.nome, solicitacao.grupo.nome))
                                    .addClass("media-heading")
                                )
                                .append(
                                    $("<button>")
                                    .attr("data-solicitacao-rescusar", solicitacao.idSolicitacao)
                                    .addClass("btn-solicitacao btn btn-default btn-xs")
                                    .append($("<i>").addClass("glyphicon glyphicon-trash"))
                                    .append(" Recusar")
                                )
                                .append(
                                    $("<button>")
                                    .attr("data-solicitacao-aceitar", solicitacao.idSolicitacao)
                                    .addClass("btn-solicitacao btn btn-default btn-xs")
                                    .append($("<i>").addClass("glyphicon glyphicon-ok"))
                                    .append(" Aceitar")
                                )
                            );
                })
            );
            this.atualizarElementos();
            if(solicitacoes.length === 0)
                this.zerarSolicitacoes();
        },
        
        zerarSolicitacoes: function() {
            this.atualizarBtnInfo(0);
            this.$dropdown.empty();
            this.$dropdown.append($("<li>").append($("<a>").text("Nenhuma notificação pendente")));
        }
    };
    
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
            this.$lista.find(".btn-solicitacao").unbind("click");
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
            
            this.$lista.find("[ver-mais]").click(function(e) {
                $(this).closest(".grupo").find(".usuarios-do-grupo").toggleClass("hidden");
                return e.preventDefault();
            });
            
            this.$lista.find("[data-solicitar-grupo]").click(function(e) {
                if(!$(this).hasClass("solicitou")) {
                    self.controller.enviarSolicitacao({idGrupo: $(this).data("solicitar-grupo")}, $(this));
                }
                return e.preventDefault();
            });
            
            this.$lista.find("[data-remover-solicitacao-grupo]").click(function(e) {
                if(!$(this).hasClass("solicitou")) {
                    self.controller.removerSolicitacao({idGrupo: $(this).data("remover-solicitacao-grupo")}, $(this));
                }
                return e.preventDefault();
            });
            
            this.$lista.find("[data-remover-grupo]").click(function(e) {
                if(!$(this).hasClass("solicitou")) {
                    self.controller.removerUsuarioDoGrupo({idGrupo: $(this).data("remover-grupo")}, $(this));
                }
                return e.preventDefault();
            });
        },
        
        marcarBtnComoGrupoSolicitado: function($btnGrupo) {
            var $icone = $btnGrupo.find("i");
            $btnGrupo.html("&nbsp;&nbsp;Solicitação enviada"); 
            $icone.toggleClass("rodar");
            $icone.removeClass("glyphicon-send");
            $icone.addClass("glyphicon-floppy-disk");
            $btnGrupo.prepend($icone);
            $btnGrupo.addClass("btn-success");
            $btnGrupo.removeClass("btn-default");
            $btnGrupo.addClass("solicitou");
        },
        
        marcarBtnComoSolicitacaoRemovida: function($btnGrupo) {
            var $icone = $btnGrupo.find("i");
            $btnGrupo.html("&nbsp;&nbsp;Solicitação enviada"); 
            $icone.toggleClass("rodar");
            $icone.removeClass("glyphicon-send");
            $icone.addClass("glyphicon-floppy-disk");
            $btnGrupo.prepend($icone);
            $btnGrupo.addClass("btn-success");
            $btnGrupo.removeClass("btn-default");
            $btnGrupo.addClass("solicitou");
        },
        
        marcarBtnComoGrupoRemovido: function($btnGrupo) {
            var $icone = $btnGrupo.find("i");
            $btnGrupo.html("&nbsp;&nbsp;Solicitação enviada"); 
            $icone.toggleClass("rodar");
            $icone.removeClass("glyphicon-send");
            $icone.addClass("glyphicon-floppy-disk");
            $btnGrupo.prepend($icone);
            $btnGrupo.addClass("btn-success");
            $btnGrupo.removeClass("btn-default");
            $btnGrupo.addClass("solicitou");
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
                                self.criarBtnSolicitacao(grupo.id, grupo.status)
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
            
            this.atualizarElementos();
        },
        
        criarNovoFiltro: function () {
            if(this.$form.find(".form-group").size() <= this.tiposDeBusca.length) {
                this.$form.prepend(this.criarFormGroup());
                this.atualizarElementos();
            }
        },
        
        criarBtnSolicitacao: function (id, status) {
            return status === null ?
                        $("<button>")
                        .attr("data-solicitar-grupo", id)
                        .addClass("btn btn-default btn-solicitacao")
                        .append($("<i>").addClass("glyphicon glyphicon-send"))
                        .append("&nbsp;&nbsp;Enviar Solicitação")
                        : status === "PENDENTE" ?
                                $("<button>")
                                .attr("data-remover-solicitacao-grupo", id)
                                .addClass("btn btn-warning btn-solicitacao")
                                .append($("<i>").addClass("glyphicon glyphicon-hourglass"))
                                .append("&nbsp;&nbsp;Remover Solicitacão")
                                :
                                $("<button>")
                                .attr("data-remover-grupo", id)
                                .addClass("btn btn-danger btn-solicitacao")
                                .append($("<i>").addClass("glyphicon glyphicon-trash"))
                                .append("&nbsp;&nbsp;Remover Grupo");
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
    App.NovasSolicitacoes.iniciar();
})();