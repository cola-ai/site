
package br.com.cwi.colaai.web.controllers.rest;

import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Érico de Souza Loewe
 */
@RestController
@RequestMapping(value = "/rest/itinerario")
public class ItinerarioRestController {
    
    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    void registrar(ItinerarioViewModel itinerario) {
        ItinerarioViewModel teste = itinerario;
    }
}
