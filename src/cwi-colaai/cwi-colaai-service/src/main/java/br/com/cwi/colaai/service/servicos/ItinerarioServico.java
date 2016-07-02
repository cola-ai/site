
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.*;
import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.service.repositorios.ItinerarioDiasDaSemanaRepositorio;
import br.com.cwi.colaai.service.repositorios.ItinerarioRepositorio;
import java.util.List;
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
    
    @Autowired
    private ItinerarioDiasDaSemanaRepositorio itinerarioDiasDaSemanaRepositorio;
    
    @Autowired
    private RotaServico rotaServico;
    
    @Autowired
    private LocalServico localServico;
    
    public void registrar(ItinerarioViewModel itinerarioViewModel) {        
        Rota rota = rotaServico.salvar(itinerarioViewModel.getRota(), itinerarioViewModel.getRota().getPassos());
        Local origem = localServico.salvar(itinerarioViewModel.getOrigem());
        Local destino = localServico.salvar(itinerarioViewModel.getDestino());
        Itinerario itinerario = itinerarioViewModel.toItinerario(rota, origem, destino);
        
        itinerarioRepositorio.save(itinerario);
        
        // sim, esse metodo deve ser chamado depois, pois só é possivel
        // fazer essa atribuição quando ja o itinerario ja existe.
        atribuirDiasDaSemanaAoItinerario(itinerarioViewModel.getDiasDaSemana(), itinerario);
    }

    private void atribuirDiasDaSemanaAoItinerario(List<DiasDaSemana> diasDaSemana, Itinerario itinerario) {
        diasDaSemana.forEach((d) -> {
            itinerarioDiasDaSemanaRepositorio.save(new ItinerarioDiasDaSemana(d, itinerario));
        });
    }
}
