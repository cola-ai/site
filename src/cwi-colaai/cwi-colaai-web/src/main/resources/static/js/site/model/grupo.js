/*
 *  Grupo
 */
"use strict";
var Grupo = function(urls) {
    this.urls = urls 
            || {
                gruposDoUsuarioAtual: "/rest/grupo/gruposDoUsuarioAtual"
               };
};

Grupo.prototype = {
    gruposDoUsuarioAtual: function() {
        return $.ajax({
            url: this.urls.gruposDoUsuarioAtual,
            method: "GET"
        });
    }
};