
package br.com.cwi.colaai.web.controllers.rest;

import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.service.servicos.ItinerarioServico;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    ItinerarioViewModel registrar(@RequestBody ItinerarioViewModel itinerario, HttpServletResponse response) {
        InformacoesUsuarioAtual usuarioAtual = getUsuarioAtual();
        itinerarioServico.registrar(itinerario, usuarioAtual.getUsuarioId());
        return itinerario;
    }
    
    private static InformacoesUsuarioAtual getUsuarioAtual() {
        return (InformacoesUsuarioAtual) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
