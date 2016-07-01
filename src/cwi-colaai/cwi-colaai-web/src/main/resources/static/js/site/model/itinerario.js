/*
 *  Itinerario
 */
var Itinerario = function(urls) {
    this.urls = urls 
            || {
                registrar: "/rest/itinerario/registrar"
               };
};

Itinerario.prototype = {
    registrar: function(itinerario) {
        return $.ajax({
            url: this.urls.registrar,
            method: "POST",
            data: itinerario
        });
    }
};