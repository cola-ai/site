"use strict";
(function () {
    window.App = window.App || {};
    
    App.CadastroUsuarioView = {
        iniciar: function() {
            this.buscarElementos();
            this.vincularEventos();
        },
        
        buscarElementos: function() {
            this.$form = $(".cadastrar-form");
        },
        
        vincularEventos: function() {
            this.$form.submit(function() {
                
            });
        }
    };
    
    App.CadastroUsuarioView.iniciar();
})();