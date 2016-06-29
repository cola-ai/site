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

            this.$form.validate({
                errorClass: "help-block",
                
                highlight: function (element) {
                    $(element).parent().removeClass("has-success");
                    $(element).parent().addClass("has-error");
                },
                
                unhighlight: function (element) {
                    $(element).parent().removeClass("has-error");
                },
                
                success: function (label) {
                    label.parent().addClass("has-success");
                },
                
                rules: {
                    nome: {
                        required: true,
                        minlength: 3
                    },
                    sobrenome: {
                        required: true,
                        minlength: 3
                    },
                    telefone: {
                        required: true,
                        minlength: 16,
                        maxlength: 17
                    },
                    sexo: {
                        required: true
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
                    },
                    arquivo: {
                        required: true
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