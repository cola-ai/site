"use strict";
(function () {
    window.App = window.App || {};

    App.CadastroUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$form = $("#cadastrar-usuario-form");
        },
        vincularEventos: function () {
            App.Mascara.telefone(this.$form.find("#telefone"));
        }
    };
    
    App.CadastroUsuarioView.iniciar();
})();