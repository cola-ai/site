/*
 * jquery validate
 */
"use strict";
(function () {
    window.App = window.App || {};

    App.Validador = {
        iniciar: function () {
            this.criarMetodos();
            this.criarRules();            
        },
        
        criarMetodos: function () {
            $.validator.addMethod("password_check", function (value) {
                return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // caracter especial
                        && /[a-z]/.test(value) // tem uma letra minuscula
                        && /\d/.test(value); // tem um digito
            }, function(params, element) {
                return !(/^[A-Za-z0-9\d=!\-@._*]*$/.test($(element).val())) ? "Sua senha deve conter um 1 caracter especial" : 
                        !(/[a-z]/.test($(element).val())) ? "Sua senha deve conter uma letra minuscula" :
                        "Sua senha deve conter um digito";
            });
            
            $.validator.addMethod("only_words", function (value) {
                return !(/[\d-!"#$%&'()*+,./:;<=>?@[\\\]_`{|}~]/.test(value));
            }, function(params, element) {
                return "Seu nome só pode conter letras e caracteres";
            });
        },
        
        criarRules: function () {
            jQuery.validator.addClassRules({
                "validar-nome": {
                    required: true,
                    minlength: 3,
                    only_words: true
                },
                "validar-sobrenome": {
                    required: true,
                    minlength: 3,
                    only_words: true
                },
                "validar-telefone": {
                    required: true,
                    minlength: 16,
                    maxlength: 17
                },
                "validar-sexo": {
                    required: true
                },
                "validar-email": {
                    required: true,
                    email: true
                },
                "validar-senha": {
                    required: true,
                    minlength: 6,
                    password_check: true
                },
                "validar-confirmarSenha": {
                    required: true,
                    equalTo: "#senha"
                },
                "validar-arquivo": {
                    required: true
                }
            });
        },
        
        nossasCustomizacoes: {
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
            messages: {
                confirmarSenha: {
                    equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
                }
            }
        }
    };

    App.Validador.iniciar();
})();