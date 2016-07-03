
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.*;
import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.service.repositorios.ItinerarioDiasDaSemanaRepositorio;
import br.com.cwi.colaai.service.repositorios.ItinerarioRepositorio;
import java.util.ArrayList;
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
    
    @Autowired
    private UsuarioServico usuarioServico;
    
    public void registrar(ItinerarioViewModel itinerarioViewModel, Long usuarioId) {
        Rota rota = rotaServico.salvar(itinerarioViewModel.getRota(), itinerarioViewModel.getRota().getPassos());
        Local origem = localServico.salvar(itinerarioViewModel.getOrigem());
        Local destino = localServico.salvar(itinerarioViewModel.getDestino());
        Usuario usuario = usuarioServico.buscarPorId(usuarioId);
        Itinerario itinerario = itinerarioViewModel.toItinerario(rota, origem, destino, usuario);
        
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
    
    public List<ItinerarioViewModel> buscarItinerariosDoUsuario(Long idUsuario){
        List<ItinerarioViewModel> itinerariosViewModel = new ArrayList<>();
        List<Itinerario> itinerarios = itinerarioRepositorio.findByUsuario_Id(idUsuario);
        itinerarios.forEach((i) -> {
            ItinerarioViewModel itinerarioViewModel = new ItinerarioViewModel();
            itinerarioViewModel.setDestino(i.getDestino().toViewModel());
            itinerarioViewModel.setOrigem(i.getOrigem().toViewModel());
            itinerarioViewModel.setHorarioSaida(i.getHorarioSaida());
            
            List<ItinerarioDiasDaSemana> itinerariosDiasDaSemana = i.getDiasDaSemana();
            List<DiasDaSemana> diasDaSemana = new ArrayList<>();
            itinerariosDiasDaSemana.forEach((d) -> {diasDaSemana.add(d.getDiaDaSemana()); });
            
            itinerarioViewModel.setDiasDaSemana(diasDaSemana);
            itinerariosViewModel.add(itinerarioViewModel);
        });      
        return itinerariosViewModel;
    }
}
