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
            this.$form.validate(App.Validador.nossasCustomizacoes);
        }
    };
    
    App.EsqueceuSuaSenhaUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$form = $("#esqueceu-senha-usuario-form");
        },
        vincularEventos: function () {
            App.Mascara.telefone(this.$form.find("#telefone"));
            this.$form.validate(App.Validador.nossasCustomizacoes);
        }
    };
    
    
    App.RecuperarSenhaUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$form = $("#recuperar-senha-usuario-form");
        },
        vincularEventos: function () {
            this.$form.validate(App.Validador.nossasCustomizacoes);
        }
    };

    App.LoginUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$form = $("#login-usuario-form");
        },
        vincularEventos: function () {
            this.$form.validate(App.Validador.nossasCustomizacoes);
        }
    };
    
    App.CadastroUsuarioView.iniciar();
    App.LoginUsuarioView.iniciar();
    App.EsqueceuSuaSenhaUsuarioView.iniciar();
    App.RecuperarSenhaUsuarioView.iniciar();
})();