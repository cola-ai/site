
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Itinerario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Érico de Souza Loewe
 */
public interface ItinerarioRepositorio extends CrudRepository<Itinerario, Long> {
    List<Itinerario> findByUsuario_IdAndGrupoIsNull(Long idUsuario);
    
    List<Itinerario> findByGrupo_Id(Long id);
    
    List<Itinerario> findByIdIn(List<Long> ids);
}
