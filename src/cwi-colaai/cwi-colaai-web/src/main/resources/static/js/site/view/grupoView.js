"use strict";
(function () {
    window.ColaAi = window.ColaAi || {};

    ColaAi.NovasSolicitacoesView = {
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
                                    .attr("data-solicitacao-rescusar", solicitacao.id)
                                    .addClass("btn-solicitacao btn btn-default btn-xs")
                                    .append($("<i>").addClass("glyphicon glyphicon-trash"))
                                    .append(" Recusar")
                                )
                                .append(
                                    $("<button>")
                                    .attr("data-solicitacao-aceitar", solicitacao.id)
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
    
    ColaAi.PesquisarGruposView = {
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
            
            this.$lista.find("[solicitar-grupo], [remover-solicitacao-grupo], [remover-grupo]").click(function(e) {
                if(!$(this).hasClass("solicitou")) {
                    self.mudarStatusBtn("AGUARDANDO", $(this));
                    if($(this).is("[solicitar-grupo]")) {
                        self.controller.enviarSolicitacao({idGrupo: $(this).data("grupo-id")}, $(this));
                    } else if($(this).is("[remover-solicitacao-grupo]")) {
                        self.controller.removerSolicitacao({idGrupo: $(this).data("grupo-id")}, $(this));
                    } else {
                        self.controller.removerUsuarioDoGrupo({idGrupo: $(this).data("grupo-id")}, $(this));
                    }
                }
                return e.preventDefault();
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
                                .append(String.format("{0} - {1} {2}", grupo.nome, grupo.quantidadeVagas - grupo.participantes.length, ColaAi.Idioma.home.grupos_de_que_participo.lista.titulo))
                                .append(self.criarIconesDeVagasDoGrupo(grupo.quantidadeVagas, grupo.participantes.length))
                            )
                            .append(
                                grupo.itinerarios.length === 0 ? "" :
                                $("<small>")
                                .addClass("media-sub-heading")
                                .text(String.format("{0} - {1} ({2})", grupo.itinerarios[0].origem.cidade, grupo.itinerarios[0].destino.cidade, grupo.itinerarios[0].horarioSaida))
                            )
                            .append(
                                $("<div>").addClass("media-list usuarios-do-grupo hidden").append(
                                    self.criarListaUsuarios(grupo.participantes)
                                )
                            )
                        ).append(
                            $("<div>")
                            .addClass("media-right")
                            .append(
                                self.criarBtnSolicitacao(grupo.id, grupo.status, grupo.participantes)
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
        
        mudarStatusBtn: function (status, $btn) {   
            var $icone = $("<i>").addClass("glyphicon"), self = this;
            
            $btn.removeClass("btn-danger btn-default btn-warning");
            
            if(status === "REMOVIDO") {                
                $btn.html("&nbsp;&nbsp;Você saiu do grupo");
                $icone.addClass("glyphicon-remove");
                $btn.prepend($icone);
                $btn.addClass("btn-success solicitou");
                
                setTimeout(function() {
                    self.criarBtnSolicitacao($btn.data("grupo-id"), null).prependTo($btn.parent());
                    $btn.remove();
                    self.atualizarElementos();
                }, 3000);
            } else if(status === "SOLICITADO") {
                $btn.html("&nbsp;&nbsp;Solicitação enviada");
                $icone.addClass("glyphicon-floppy-disk");
                $btn.prepend($icone);
                $btn.addClass("btn-success solicitou");
                
                setTimeout(function() {
                    self.criarBtnSolicitacao($btn.data("grupo-id"), "PENDENTE").prependTo($btn.parent());
                    $btn.remove();
                    self.atualizarElementos();
                }, 3000);
            } else if(status === "SOLICITACAO_REMOVIDA") {
                $btn.html("&nbsp;&nbsp;Solicitação removida");
                $icone.addClass("glyphicon-trash");
                $btn.prepend($icone);
                $btn.addClass("btn-success solicitou");
                
                setTimeout(function() {
                    self.criarBtnSolicitacao($btn.data("grupo-id"), null).prependTo($btn.parent());
                    $btn.remove();
                    self.atualizarElementos();
                }, 3000);
            } else {
                $btn.html("&nbsp;&nbsp;Aguarde...");
                $icone.addClass("glyphicon-hourglass");
                $btn.prepend($icone);
            }
        },
        
        criarListaUsuarios: function (participantes) {
            if(participantes.length === 0) {
                return $("<div>").addClass("alert alert-info").text("Esse grupo não possui usuarios cadastrados");
            }
            
            return participantes.map(function(usuario) {
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
                });
        },        
        
        criarBtnSolicitacao: function (id, status, participantes) {
            var usuarioPertenceAoGrupo = true;
            var $btn = $("<button>")
                        .attr("data-grupo-id", id)
                        .addClass("btn btn-solicitacao");
            if(participantes !== undefined) {
                usuarioPertenceAoGrupo = participantes.filter(function (participante) { return ColaAi.Usuario.atual.idUsuario === participante.id }).length > 0;
            }
            return status === "APROVADA" && usuarioPertenceAoGrupo ?
                        $btn
                        .attr("remover-grupo", "")
                        .addClass("btn-danger")
                        .append($("<i>").addClass("glyphicon glyphicon-trash"))
                        .append("&nbsp;&nbsp;Remover Grupo")
                        : status === "PENDENTE" ?
                                $btn
                                .attr("remover-solicitacao-grupo", "")
                                .addClass("btn-warning")
                                .append($("<i>").addClass("glyphicon glyphicon-hourglass"))
                                .append("&nbsp;&nbsp;Sair do grupo")
                                :
                                $btn
                                .attr("solicitar-grupo", "")
                                .addClass("btn-default")
                                .append($("<i>").addClass("glyphicon glyphicon-send"))
                                .append("&nbsp;&nbsp;Enviar Solicitação");
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

    ColaAi.ListarGruposRecomendadosView = {
        status: "VAZIA", // "VAZIA", "CARREGANDO" e "CARREGADA"
        controller: undefined,
        
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
            this.mudarStatusLista("CARREGANDO");
            this.controller = new GrupoController(this);
            this.controller.gruposRecomendados();
        },
        
        buscarElementos: function () {
            this.$lista = $(".lista-de-grupos-recomendados-home");
        },
        
        atualizarElementos: function () {
            this.$lista = $(".lista-de-grupos-recomendados-home");
            
            this.$lista.find(".btn-solicitacao").unbind("click");
            
            this.vincularEventos();
        },
        
        vincularEventos: function () {
            var self = this;
            
            this.$lista.find("[solicitar-grupo], [remover-solicitacao-grupo], [remover-grupo]").click(function(e) {
                if(!$(this).hasClass("solicitou")) {
                    self.mudarStatusBtn("AGUARDANDO", $(this));
                    if($(this).is("[solicitar-grupo]")) {
                        self.controller.enviarSolicitacao({idGrupo: $(this).data("grupo-id")}, $(this));
                    } else if($(this).is("[remover-solicitacao-grupo]")) {
                        self.controller.removerSolicitacao({idGrupo: $(this).data("grupo-id")}, $(this));
                    } else {
                        self.controller.removerUsuarioDoGrupo({idGrupo: $(this).data("grupo-id")}, $(this));
                    }
                }
                return e.preventDefault();
            });
        },
        
        mudarStatusBtn: function (status, $btn) {   
            var $icone = $("<i>").addClass("glyphicon"), self = this;
            
            $btn.removeClass("btn-danger btn-default btn-warning");
            
            if(status === "REMOVIDO") {                
                $btn.html("&nbsp;&nbsp;Você saiu do grupo");
                $icone.addClass("glyphicon-remove");
                $btn.prepend($icone);
                $btn.addClass("btn-success solicitou");
                
                setTimeout(function() {
                    self.criarBtnSolicitacao($btn.data("grupo-id"), null).appendTo($btn.parent());
                    $btn.remove();
                    self.atualizarElementos();
                }, 3000);
            } else if(status === "SOLICITADO") {
                $btn.html("&nbsp;&nbsp;Solicitação enviada");
                $icone.addClass("glyphicon-floppy-disk");
                $btn.prepend($icone);
                $btn.addClass("btn-success solicitou");
                
                setTimeout(function() {
                    self.criarBtnSolicitacao($btn.data("grupo-id"), "PENDENTE").appendTo($btn.parent());
                    $btn.remove();
                    self.atualizarElementos();
                }, 3000);
            } else if(status === "SOLICITACAO_REMOVIDA") {
                $btn.html("&nbsp;&nbsp;Solicitação removida");
                $icone.addClass("glyphicon-trash");
                $btn.prepend($icone);
                $btn.addClass("btn-success solicitou");
                
                setTimeout(function() {
                    self.criarBtnSolicitacao($btn.data("grupo-id"), null).appendTo($btn.parent());
                    $btn.remove();
                    self.atualizarElementos();
                }, 3000);
            } else {
                $btn.html("&nbsp;&nbsp;Aguarde...");
                $icone.addClass("glyphicon-hourglass");
                $btn.prepend($icone);
            }
        },
        
        mudarStatusLista: function (status) {   
            if(status === "VAZIA") {
                this.$lista.html($("<div>").addClass("alert alert-info").text("Desculpe, mas nos não temos recomendações para você :("));
            } else if(status === "CARREGADA") {
                this.$lista.empty();
            } else {
                this.$lista.html($("<i>").addClass("glyphicon glyphicon-refresh rodar-infinitamente icone-carregando"));
            }
            
            this.status = status;
        },
        
        preencherLista: function (grupos) {
            var self = this;
            
            this.mudarStatusLista("CARREGADA");
            
            this.$lista.append(grupos.map(function(grupo) {
                return $("<div>")
                        .addClass("media grupo")
                        .append(
                            $("<div>")
                            .addClass("media-body")
                            .append(
                                $("<h5>")
                                .addClass("media-heading")
                                .append(String.format("{0} - {1} {2}", grupo.nome, grupo.quantidadeVagas - grupo.participantes.length, ColaAi.Idioma.home.grupos_de_que_participo.lista.titulo))
                                .append(self.criarIconesDeVagasDoGrupo(grupo.quantidadeVagas, grupo.participantes.length))
                            )                            
                            .append(
                                grupo.itinerarios.length === 0 ? "" :
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
                            ).append(
                                self.criarBtnSolicitacao(grupo.id, grupo.status, grupo.participantes)
                            )                            
                        );
            }));
            
            if(grupos.length === 0) {
                this.mudarStatusLista("VAZIA");
            }
            
            self.atualizarElementos();
        },
        
        criarBtnSolicitacao: function (id, status, participantes) {
            var usuarioPertenceAoGrupo = true;
            var $btn = $("<button>")
                        .attr("data-grupo-id", id)
                        .addClass("btn btn-solicitacao");
            if(participantes !== undefined) {
                usuarioPertenceAoGrupo = participantes.filter(function (participante) { return ColaAi.Usuario.atual.idUsuario === participante.id }).length > 0;
            }
            return status === "APROVADA" && usuarioPertenceAoGrupo ?
                        $btn
                        .attr("remover-grupo", "")
                        .addClass("btn-danger")
                        .append($("<i>").addClass("glyphicon glyphicon-trash"))
                        .append("&nbsp;&nbsp;Remover Grupo")
                        : status === "PENDENTE" ?
                                $btn
                                .attr("remover-solicitacao-grupo", "")
                                .addClass("btn-warning")
                                .append($("<i>").addClass("glyphicon glyphicon-hourglass"))
                                .append("&nbsp;&nbsp;Sair do grupo")
                                :
                                $btn
                                .attr("solicitar-grupo", "")
                                .addClass("btn-default")
                                .append($("<i>").addClass("glyphicon glyphicon-send"))
                                .append("&nbsp;&nbsp;Enviar Solicitação");
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

    ColaAi.ListarGruposDoUsuarioView = {
        status: "VAZIA", // "VAZIA", "CARREGANDO" e "CARREGADA"
        controller: undefined,
        
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
            this.mudarStatusLista("CARREGANDO");
            this.controller = new GrupoController(this);
            this.controller.listar();
        },
        
        buscarElementos: function () {
            this.$lista = $(".lista-de-grupos-home");
        },
        
        vincularEventos: function () {
            
        },
        
        mudarStatusLista: function (status) {   
            if(status === "VAZIA") {
                this.$lista.html($("<div>").addClass("alert alert-info").text("Voce não esta cadastrado em nenhum grupo"));
            } else if(status === "CARREGADA") {
                this.$lista.empty();
            } else {
                this.$lista.html($("<i>").addClass("glyphicon glyphicon-refresh rodar-infinitamente icone-carregando"));
            }
            
            this.status = status;
        },
        
        preencherLista: function (grupos) {
            var self = this;
            
            this.mudarStatusLista("CARREGADA");
            
            this.$lista.append(grupos.map(function(grupo) {
                return $("<div>")
                        .addClass("media grupo")
                        .append(
                            $("<div>")
                            .addClass("media-body")
                            .append(
                                $("<h4>")
                                .addClass("media-heading")
                                .append(String.format("{0} - {1} {2}", grupo.nome, grupo.quantidadeVagas - grupo.participantes.length, ColaAi.Idioma.home.grupos_de_que_participo.lista.titulo))
                                .append(self.criarIconesDeVagasDoGrupo(grupo.quantidadeVagas, grupo.participantes.length))
                                .prepend(ColaAi.Usuario.atual.idUsuario === grupo.lider.id ? $("<i>").addClass("glyphicon glyphicon-sunglasses").append(" ") : "")
                            )
                            .append(
                                grupo.itinerarios.length === 0 ? "" :
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
            
            if(grupos.length === 0) {
                this.mudarStatusLista("VAZIA");
            }            
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
    
    if($("#pesquisar-grupo-form").size() > 0) {
        ColaAi.PesquisarGruposView.iniciar();
    }    
    if($(".lista-de-grupos-recomendados-home").size() > 0) {
        ColaAi.ListarGruposRecomendadosView.iniciar();
    }    
    if($(".lista-de-grupos-home").size() > 0) {
        ColaAi.ListarGruposDoUsuarioView.iniciar();
    }
    if($(".solicitacoes-dropdown").size() > 0) {
        ColaAi.NovasSolicitacoesView.iniciar();
    }    
})();