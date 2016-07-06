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
            this.$form.find("#telefone").mask("(00) 0000 - 00000");
        }
    };
    
    ColaAi.ConfiguracoesUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$alterarCadastroform = $("#alterar-cadastro-form");
            this.$alterarSenhaform = $("#alterar-senha-form");
            this.$alterarImagemform = $("#alterar-imagem-form");
        },
        vincularEventos: function () {
            this.$alterarCadastroform.find("#telefone").mask("(00) 0000 - 00000");
            
            this.$alterarSenhaform.validate(ColaAi.Validador.nossasCustomizacoes);
            this.$alterarImagemform.validate(ColaAi.Validador.nossasCustomizacoes);
        }
    };
    
    ColaAi.ConfiguracoesUsuarioView.iniciar();
    ColaAi.CadastroUsuarioView.iniciar();
})();