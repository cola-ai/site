"use strict";
(function () {
    window.ColaAi = window.ColaAi || {};

    ColaAi.Mascara = {
        telefone: function($elemento) {
            $elemento.on("keypress", function () {
                $(this).val($(this).val()
                        .replace(/^(\d{2})(\d)/g, "($1) $2")
                        .replace(/^(.{9})(\d)/g, "$1 - $2"));
            });
        },
        hora: function($elemento) {
            $elemento.on("keypress", function () {
                $(this).val($(this).val()
                        .replace(/^(\d{2})(\d)/g, "$1:$2"));
            });
        }
    };
})();