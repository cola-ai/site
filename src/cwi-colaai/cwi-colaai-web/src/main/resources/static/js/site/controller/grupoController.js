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
    
    pesquisar: function (filtro) {
        var self = this;
        
        this.grupo.pesquisar(filtro)
                .done(function (grupos) {
                    self.view.preencherLista(grupos);
                });
    },
    
    enviarSolicitacao: function (grupo, $btnGrupo) {
        var self = this;
        
        this.grupo.enviarSolicitacao(grupo)
                .done(function (data) {
                    self.view.marcarBtnComoGrupoSolicitado($btnGrupo);
                });
    },
    
    removerSolicitacao: function (grupo, $btnGrupo) {
        var self = this;
        
        this.grupo.removerSolicitacao(grupo)
                .done(function (data) {
                    self.view.marcarBtnComoSolicitacaoRemovida($btnGrupo);
                });
    },
    
    removerUsuarioDoGrupo: function (grupo, $btnGrupo) {
        var self = this;
        
        this.grupo.removerUsuarioDoGrupo(grupo)
                .done(function (data) {
                    self.view.marcarBtnComoGrupoRemovido($btnGrupo);
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