/*
 * jquery validate
 */
"use strict";
(function () {
    window.ColaAi = window.ColaAi || {};

    ColaAi.Validador = {
        iniciar: function () {
            this.criarMetodos();
            this.criarRules();
            this.buscarElementos();
            this.vincularEventos();
        },
        buscarElementos: function () {
            this.$form = $(".formulario-validado");
        },
        vincularEventos: function () {
            this.$form.validate(this.nossasCustomizacoes);
        },
        criarMetodos: function () {
            $.validator.addMethod("password_check", function (value) {
                return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // caracter especial
                        && /[a-z]/.test(value) // tem uma letra minuscula
                        && /\d/.test(value); // tem um digito
            }, function (params, element) {
                return !(/^[A-Za-z0-9\d=!\-@._*]*$/.test($(element).val())) ? "Sua senha deve conter um 1 caracter especial" :
                        !(/[a-z]/.test($(element).val())) ? "Sua senha deve conter uma letra minuscula" :
                        "Sua senha deve conter um digito";
            });

            $.validator.addMethod("only_words", function (value) {
                return !(/[\d!"#$%&'()*+,./:;<=>?@[\\\]_`{|}~]/.test(value));
            }, function (params, element) {
                return "Seu nome só pode conter letras e caracteres";
            });

            $.validator.addMethod('filesize', function (value, element, param) {
                return this.optional(element) || (element.files[0].size <= param);
            }, function (params, element) {
                return String.format("O tamanho da imagem não pode ser maior que");
            });
        },
        criarRules: function () {
            jQuery.validator.addClassRules({
                "validar-nome": {
                    required: true,
                    minlength: 3,
                    only_words: true,
                    maxlength: 255
                },
                "validar-sobrenome": {
                    required: true,
                    minlength: 3,
                    only_words: true,
                    maxlength: 255
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
                    email: true,
                    maxlength: 255
                },
                "validar-email-existe": {
                    remote: "/rest/usuario/existeUsuarioComEmail"
                },
                "validar-email-nao-existe": {
                    remote: "/rest/usuario/naoExisteUsuarioComEmail"
                },
                "validar-senha": {
                    required: true,
                    minlength: 6,
                    password_check: true,
                    maxlength: 50
                },
                "validar-confirmarSenha": {
                    required: true,
                    equalTo: "#senha"
                },
                "validar-arquivo": {
                    required: true
                },
                "validar-tamanho-arquivo": {
                    filesize: 1048576
                },           
                "validar-numero-positivo": {
                    min: 0
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
                email: {
                    remote: jQuery.validator.format("{0} já esta em uso")
                },
                "validar-email-nao-existe": {
                    remote: jQuery.validator.format("{0} não existe")
                },
                confirmarSenha: {
                    equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
                },
                file: {
                    filesize: "O arquivo deve ter menos que 1MB"
                }
            }
        }
    };

    ColaAi.Validador.iniciar();
})();