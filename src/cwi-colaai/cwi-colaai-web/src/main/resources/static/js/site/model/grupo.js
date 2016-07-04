/*
 *  Grupo
 */
"use strict";
var Grupo = function(urls) {
    this.urls = urls 
            || {
                pesquisar: "/rest/grupo/pesquisarComFiltro",
                gruposDoUsuarioAtual: "/rest/grupo/gruposDoUsuarioAtual"
               };
};

Grupo.prototype = {
    gruposDoUsuarioAtual: function() {
        return $.ajax({
            url: this.urls.gruposDoUsuarioAtual,
            method: "GET"
        });
    },
    
    pesquisar: function(filtro) {
        return $.ajax({
            url: this.urls.pesquisar,
            method: "GET",
            data: filtro
        });
    }
};