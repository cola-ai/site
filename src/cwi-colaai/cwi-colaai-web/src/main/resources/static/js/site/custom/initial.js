"use strict";

/*
 *  Ajax
 */
$(document)
    .ajaxError(function() {
        console.log("Tratador de erros genérico.", arguments);
    });

/*
 *  String
 */
// créditos: http://stackoverflow.com/a/4673436
if (!String.format) {
    String.format = function (format) {
        var args = Array.prototype.slice.call(arguments, 1);
        return format.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] !== "undefined"
              ? args[number]
              : match
            ;
        });
    };
}

String.prototype.toCapitalizeCase = function() {
    return this.charAt(0).toUpperCase() + this.toLowerCase().slice(1);
};

Array.prototype.contains = function(item) { 
    return this.indexOf(item) !== -1; 
};

if (!String.empty) {
    String.empty = function (str) {
        return str === null || str === "";
    };
}