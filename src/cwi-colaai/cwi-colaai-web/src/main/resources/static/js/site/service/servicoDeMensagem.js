(function () {
    window.App = window.App || {};
    
    App.Mensagem = {
        informativa: function(mensagem, obj) {
            this.apresentarMensagem(mensagem, "alert-info", obj);
        },

        erro: function (mensagem, obj) {
            this.apresentarMensagem(mensagem, "alert-danger", obj);
        },

        sucesso: function (mensagem, obj) {
            this.apresentarMensagem(mensagem, "alert-success", obj);
        },

        aviso: function (mensagem, obj) {
            this.apresentarMensagem(mensagem, "alert-warning", obj);
        },
        
        apresentarMensagem: function(mensagem, tipo, obj) {
            $(obj).prepend(
                $("<div>").addClass("alert")
                            .addClass(tipo)
                            .prepend(mensagem));
        }
    };

    App.Modal = {
        $modal: null,

        iniciar: function () {
            this.buscarElementos();
            this.vincularEventos();
        },

        buscarElementos: function() {
            this.$modal = $("#cola-ai-modal-notificacoes");
        },

        vincularEventos: function() {
            var self = this;

            this.$modal.on("hide.bs.modal", function (event) {
                self.$modal.find(".modal-body").empty();
                self.$modal.find(".modal-footer").find("[message-btn-delete]").remove();
                $(".modal-backdrop.fade.in").remove();
            });
        },

        informativa: function (mensagem) {
            this.apresentarMensagem("Mensagem informativa", mensagem, "alert-info");
        },

        erro: function (mensagem) {
            this.apresentarMensagem("Mensagem de erro", mensagem, "alert-danger");
        },

        sucesso: function (mensagem) {
            this.apresentarMensagem("Mensagem de sucesso", mensagem, "alert-success");
        },

        aviso: function (mensagem) {
            this.apresentarMensagem("Mensagem de aviso", mensagem, "alert-warning");
        },

        confirmacao: function (mensagem, $btn) {
            this.$modal.find(".modal-header > .modal-title").text("Mesagem de confirmação");
            this.$modal.find(".modal-body").append(mensagem);
            this.$modal.find(".modal-footer").append($btn);
            this.$modal.modal("toggle");
        },

        apresentarMensagem: function (titulo, mensagem, tipo) {
            this.$modal.find(".modal-header > .modal-title").text(titulo);
            this.$modal.find(".modal-body")
                        .append(
                            $("<div>").addClass("alert")
                                        .addClass(tipo)
                                        .prepend(mensagem));
            this.$modal.modal("toggle");
        }
    };

    App.Modal.iniciar();
})();