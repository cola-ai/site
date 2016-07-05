"use strict";
var GrupoController = function (view) {
    this.view = view;
    this.grupo = new Grupo();
};

GrupoController.prototype = {
    listar: function () {
        var self = this;
        this.grupo.gruposDoUsuarioAtual()
                .done(function (grupos) {
                    self.view.preencherLista(grupos);
                });
    },
    
    gruposRecomendados: function () {
        var self = this;
        this.grupo.gruposRecomendados()
                .done(function (grupos) {
                    self.view.preencherLista(grupos);
                });
    },
    
    pesquisar: function (filtro) {
        var self = this;
        
        this.grupo.pesquisar(filtro)
                .done(function (grupos) {
                    self.view.preencherLista(grupos);
                });
    },
    
    aceitarSolicitacao: function (solicitacao) {        
        this.grupo.aceitarSolicitacao(solicitacao);
    },
    
    fecharSolicitacao: function (solicitacao) {
        this.grupo.recusarSolicitacao(solicitacao);
    },
    
    enviarSolicitacao: function (grupo, $btnGrupo) {
        var self = this;
        
        this.grupo.enviarSolicitacao(grupo)
                .done(function (data) {
                    self.view.mudarStatusBtn("SOLICITADO", $btnGrupo);
                });
    },
    
    removerSolicitacao: function (grupo, $btnGrupo) {
        var self = this;
        
        this.grupo.removerSolicitacao(grupo)
                .done(function (data) {
                    self.view.mudarStatusBtn("SOLICITACAO_REMOVIDA", $btnGrupo);
                });
    },
    
    removerUsuarioDoGrupo: function (grupo, $btnGrupo) {
        var self = this;
        
        this.grupo.removerUsuarioDoGrupo(grupo)
                .done(function (data) {
                    self.view.mudarStatusBtn("REMOVIDO", $btnGrupo);
                });
    },
    
    atualizarSolicitacoes: function () {
        var self = this;
        
        this.grupo
            .minhasSolicitacoes()
            .done(function(solicitacoes) {
                self.view.apresentarSolicitacoes(solicitacoes);
            });
    }
};