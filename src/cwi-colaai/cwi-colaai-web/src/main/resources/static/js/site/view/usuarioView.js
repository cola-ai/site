"use strict";
(function () {
    window.App = window.App || {};

    App.CadastroUsuarioView = {
        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$form = $(".cadastrar-form");
        },
        vincularEventos: function () {
            this.$form.validate({
                rules: {
                    nome: {
                        required: true,
                        minlength: 3
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    senha: {
                        required: true
                    },
                    confirmarSenha: {
                        required: true,
                        equalTo: "#senha"
                    }
                },
                messages: {
                    nome: {
                        required: "O campo nome é obrigatório.",
                        minlength: "O campo nome deve conter no mínimo 3 caracteres."
                    },
                    email: {
                        required: "O campo email é obrigatório.",
                        email: "O campo email deve conter um email válido."
                    },
                    senha: {
                        required: "O campo senha é obrigatório."
                    },
                    confirmarSenha: {
                        required: "O campo confirmação de senha é obrigatório.",
                        equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
                    }
                }
            });
        }
    };

    App.CadastroUsuarioView.iniciar();
})();