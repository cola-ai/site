/*
 *  Grupo
 */
"use strict";
var Grupo = function (urls) {
    this.urls = urls
            || {
                pesquisar: "/rest/grupo/pesquisarComFiltro",
                gruposDoUsuarioAtual: "/rest/grupo/gruposDoUsuarioAtual",
                enviarSolicitacao: "/rest/grupo/enviarSolicitacao",
                removerSolicitacao: "/rest/grupo/removerSolicitacao",
                removerUsuarioDoGrupo: "/rest/grupo/removerUsuarioDoGrupo",
                minhasSolicitacoes: "/rest/grupo/minhasSolicitacoes",
                gruposRecomendados: "/rest/grupo/gruposRecomendados",
                aceitarSolicitacao: "/rest/grupo/aceitarSolicitacao",
                recusarSolicitacao: "/rest/grupo/recusarSolicitacao"
            };
};

Grupo.prototype = {
    gruposDoUsuarioAtual: function () {
        return $.ajax({
            url: this.urls.gruposDoUsuarioAtual,
            method: "GET"
        });
    },
    
    gruposRecomendados: function () {
        return $.ajax({
            url: this.urls.gruposRecomendados,
            method: "GET"
        });
    },
    
    pesquisar: function (filtro) {
        return $.ajax({
            url: this.urls.pesquisar,
            method: "GET",
            data: filtro
        });
    },
    
    enviarSolicitacao: function (grupo) {
        return $.ajax({
            url: this.urls.enviarSolicitacao,
            method: "POST",
            data: grupo
        });
    },
    
    aceitarSolicitacao: function (solicitacao) {
        return $.ajax({
            url: this.urls.aceitarSolicitacao,
            method: "POST",
            data: solicitacao
        });
    },
    
    recusarSolicitacao: function (solicitacao) {
        return $.ajax({
            url: this.urls.recusarSolicitacao,
            method: "POST",
            data: solicitacao
        });
    },
    
    removerSolicitacao: function (grupo) {
        return $.ajax({
            url: this.urls.removerSolicitacao,
            method: "POST",
            data: grupo
        });
    },
    
    removerUsuarioDoGrupo: function (grupo) {
        return $.ajax({
            url: this.urls.removerUsuarioDoGrupo,
            method: "POST",
            data: grupo
        });
    },
    
    minhasSolicitacoes: function() {
        return $.ajax({
            url: this.urls.minhasSolicitacoes,
            method: "GET"
        });
    }
};