
package br.com.cwi.colaai.web.controllers.rest;

import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.service.servicos.ItinerarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @Autowired
    ItinerarioServico itinerarioServico;
    
    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    String registrar(@RequestBody ItinerarioViewModel itinerario) {
        itinerarioServico.registrar(itinerario);
        return "OK";
    }
}
