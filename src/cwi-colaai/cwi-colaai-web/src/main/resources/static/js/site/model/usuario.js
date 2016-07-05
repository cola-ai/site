/*
 *  Usuario
 */
"use strict";
var Usuario = function(urls) {
    this.urls = urls 
            || {
                getUsuarioAtual: "/rest/usuario/getUsuarioAtual"
               };
    
};

Usuario.prototype = {
    atual: function () {
        return $.ajax({
            url: this.urls.getUsuarioAtual,
            method: "GET"
        });
    }
};

ColaAi.Usuario.iniciar();