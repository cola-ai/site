"use strict";
(function () {
    window.App = window.App || {};

    App.Mascara = {
        telefone: function($elemento) {
            $elemento.keyup(function () {
                $(this).val($(this).val()
                        .replace(/^(\d{2})(\d)/g, "($1) $2")
                        .replace(/^(.{9})(\d)/g, "$1 - $2"));
            });
        }
    };
})();