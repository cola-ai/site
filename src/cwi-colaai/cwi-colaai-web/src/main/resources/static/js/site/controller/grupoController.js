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
    }
};