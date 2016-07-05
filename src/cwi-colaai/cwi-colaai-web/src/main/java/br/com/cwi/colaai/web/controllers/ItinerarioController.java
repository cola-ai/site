
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.repositorios.ItinerarioRepositorio;
import br.com.cwi.colaai.service.servicos.ItinerarioServico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Érico de Souza Loewe
 */
@Controller
@RequestMapping(value = "/itinerario")
public class ItinerarioController {
    
    @Autowired
    SocialUserDetailsService userDetailsService;
    
    @Autowired 
    ItinerarioServico itinerarioServico;
    
    @RequestMapping(value = "/registrar", method = RequestMethod.GET)
    String registrar() {
        return "itinerario/registrar";
    }
    
    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    String buscar() {
        return "itinerario/buscar";
    }
    
    @RequestMapping(value = "/administrarItinerario")
    public String adminiatrarItinerario(Model model){
        Long idUsuarioLogado = userDetailsService.getInformacoesUsuarioAtual().getUsuarioId();
        List<ItinerarioViewModel> itinerariosDoUsuario = itinerarioServico.buscarItinerariosDoUsuario(idUsuarioLogado);
        model.addAttribute("listaItinerario", itinerariosDoUsuario);
        return "itinerario/administrarItinerario";
    }
    @RequestMapping(value ="/removerItinerario")
    public String removerItinerario(int id){
        boolean removido = itinerarioServico.removerItinerario(new Long(id));
        
        if (removido) {
            return "redirect:administrarItinerario";
        }
        return "redirect:administrarItinerario?error";
    }
    
}
