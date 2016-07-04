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
                    console.log(grupos);
                    self.view.preencherLista(grupos);                    
                });
    }    
};