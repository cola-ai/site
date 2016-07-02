
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.service.repositorios.ItinerarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Érico de Souza Loewe
 */
@Service
public class ItinerarioServico {
    
    @Autowired
    private ItinerarioRepositorio itinerarioRepositorio;
    
    public void registrar(ItinerarioViewModel itinerarioViewModel) {
        Itinerario itinerario = itinerarioViewModel.toItinerario();
        
        itinerarioRepositorio.save(itinerario);
    }
}
