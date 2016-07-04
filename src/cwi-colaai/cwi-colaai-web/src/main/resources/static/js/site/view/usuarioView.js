"use strict";
(function () {
    window.ColaAi = window.ColaAi || {};

    ColaAi.CadastroUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$form = $("#cadastrar-usuario-form");
        },
        vincularEventos: function () {
            ColaAi.Mascara.telefone(this.$form.find("#telefone"));
        }
    };
    
    ColaAi.ConfiguracoesUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$alterarCadastroform = $("#alterar-cadastro-form");
            this.$alterarImagemform = $("#alterar-imagem-form");
        },
        vincularEventos: function () {
            ColaAi.Mascara.telefone(this.$alterarCadastroform.find("#telefone"));
            
            this.$alterarImagemform.find("#file").click(function() {
                
            });
        }
    };
    
    ColaAi.ConfiguracoesUsuarioView.iniciar();
    ColaAi.CadastroUsuarioView.iniciar();
})();