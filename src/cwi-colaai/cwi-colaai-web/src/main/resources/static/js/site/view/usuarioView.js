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
    
    App.ConfiguracoesUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$alterarCadastroform = $("#alterar-cadastro-form");
            this.$alterarImagemform = $("#alterar-imagem-form");
        },
        vincularEventos: function () {
            App.Mascara.telefone(this.$alterarCadastroform.find("#telefone"));
            
            this.$alterarImagemform.find("#file").click(function() {
                
            });
        }
    };
    
    App.ConfiguracoesUsuarioView.iniciar();
    App.CadastroUsuarioView.iniciar();
})();