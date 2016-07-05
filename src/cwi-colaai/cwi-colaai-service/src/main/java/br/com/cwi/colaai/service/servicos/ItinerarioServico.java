
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.*;
import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.service.repositorios.ItinerarioDiasDaSemanaRepositorio;
import br.com.cwi.colaai.service.repositorios.ItinerarioRepositorio;
import br.com.cwi.colaai.service.repositorios.TrajetoRepositorio;
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
    
    private final static Double DISTANCIA_PADRAO = 0.05;
    
    @Autowired
    private ItinerarioRepositorio itinerarioRepositorio;
    
    @Autowired
    private TrajetoRepositorio trajetoRepositorio;
    
    @Autowired
    private ItinerarioDiasDaSemanaRepositorio itinerarioDiasDaSemanaRepositorio;
    
    @Autowired
    private RotaServico rotaServico;
    
    @Autowired
    private LocalServico localServico;
    
    @Autowired
    private UsuarioServico usuarioServico;
    
    @Autowired
    private GeocalizacaoServico geocalizacaoServico;
    
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
        List<ItinerarioDiasDaSemana> itinerarioDiasDaSemana = new ArrayList<>();
        diasDaSemana.forEach((d) -> {
            itinerarioDiasDaSemana.add(new ItinerarioDiasDaSemana(d, itinerario));
        });
        itinerarioDiasDaSemanaRepositorio.save(itinerarioDiasDaSemana);
    }
    
    public List<ItinerarioViewModel> buscarItinerariosDoUsuario(Long idUsuario){
        List<Itinerario> itinerarios = itinerarioRepositorio.findByUsuario_IdAndGrupoIsNull(idUsuario);
        return toListViewModel(itinerarios);
    }
    
    public List<ItinerarioViewModel> buscarItinerariosDoGrupo(Long idGrupo){
        List<Itinerario> itinerarios = itinerarioRepositorio.findByGrupo_Id(idGrupo);
        return toListViewModel(itinerarios);
    }
    
    
    private List<ItinerarioViewModel> toListViewModel(List<Itinerario> itinerarios){
        List<ItinerarioViewModel> itinerariosViewModel = new ArrayList<>();
        itinerarios.forEach((i) -> {
            ItinerarioViewModel itinerarioViewModel = new ItinerarioViewModel();
            itinerarioViewModel.setItinerarioId(i.getId());
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
    
    public List<Itinerario> getItinerariosRelacionados(Usuario usuario) {
        List<Itinerario> itinerarios = new ArrayList<>();
                
        final List<Trajeto> findAllPorUsuario = trajetoRepositorio.findAllPorUsuario(usuario);
        
        for(Itinerario iti : usuario.getItinerarios()) {
            String horarioSaida = iti.getHorarioSaida();
            List<DiasDaSemana> diasDaSemana = iti.getEnumDiasDaSemana();
            List<Trajeto> trajetosRecomendados = trajetoRepositorio.findAllRecomendados(horarioSaida, diasDaSemana, usuario);
            
            for(Trajeto trajeto : findAllPorUsuario) {
            
                for(Trajeto t : trajetosRecomendados) {
                    Double valorAbsoluto = geocalizacaoServico.getValorAbsoluto(trajeto.getLocalizacao(), t.getLocalizacao());
                    
                    if(valorAbsoluto <= DISTANCIA_PADRAO) {
                        for (Itinerario i : t.getPasso().getRota().getItinerarios()) {
                            if(!itinerarios.contains(i) && i.getGrupo() != null) {
                                itinerarios.add(i);
                            }
                            if(itinerarios.size() == 3) {
                                return itinerarios;
                            }
                        }
                    }
                }
            }
        }
        
        return itinerarios;
    }

}
