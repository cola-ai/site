/* global Notification */

﻿(function () {
    window.App = window.App || {};

    App.ServicoDeNotificacao = {
        notificar: function (mensagem, title, img) {
            Notification.requestPermission().then(function (result) {
                console.log(result);
                if (result === "granted") {
                    var options = {
                        body: mensagem,
                        icon: img || ""
                    }
                    new Notification(title || "", options);
                }
            });
        }
    };
})();