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
            console.log(this.$form);

            this.$form.find("#telefone").keyup(function () {
                $(this).val($(this).val()
                        .replace(/^(\d{2})(\d)/g, "($1) $2")
                        .replace(/(\d \d{4}) - (\d{4})$/, "$1 - $2"));
            });

            this.$form.validate({
                rules: {
                    nome: {
                        required: true,
                        minlength: 3
                    },
                    telefone: {
                        required: true,
                        minlength: 14,
                        maxlength: 15,
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
                    confirmarSenha: {
                        equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
                    }
                }
            });
        }
    };

    App.CadastroUsuarioView.iniciar();
})();