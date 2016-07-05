(function () {
    window.ColaAi = window.ColaAi || {};
    
    ColaAi.Usuario = {
        usuario: undefined,
        atual: undefined,
        
        iniciar: function() {
            var self = this;
            this.usuario = new Usuario();
            this.usuario.atual()
                .done(function(usuario) {
                    self.atual = usuario;
                });
        }
    };
})();