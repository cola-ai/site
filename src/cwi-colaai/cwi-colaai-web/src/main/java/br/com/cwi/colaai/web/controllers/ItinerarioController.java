
package br.com.cwi.colaai.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Érico de Souza Loewe
 */
@Controller
@RequestMapping(value = "/itinerario")
public class ItinerarioController {
    
    @RequestMapping(value = "/registrar", method = RequestMethod.GET)
    String registrar() {
        return "itinerario/registrar";
    }
}
