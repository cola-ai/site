
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.DiasDaSemana;
import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.Trajeto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Érico de Souza Loewe
 */
public interface ItinerarioRepositorio extends CrudRepository<Itinerario, Long> {
    List<Itinerario> findByUsuario_IdAndGrupoIsNull(Long idUsuario);
    
    List<Itinerario> findByGrupo_Id(Long id);
    
    List<Itinerario> findByIdIn(List<Long> ids);
    
    List<Itinerario> findByOrigem_CidadeContainingIgnoreCaseAndDestino_CidadeContainingIgnoreCaseAndHorarioSaidaContaining(String cidadeOrigem, String cidadeDestino, String horarioSaida);
    
    //List<Itinerario> findByDestino_CidadeContainingIgnoreCase(String cidade);
    
    //List<Itinerario> findByHorarioSaida(String horarioSaida);


    @Query("SELECT DISTINCT t FROM Itinerario i JOIN i.diasDaSemana dds JOIN i.rota r "
            + "JOIN r.passos p JOIN p.trajetoria t JOIN t.localizacao geo "
            + "WHERE i.horarioSaida = :horaSaida AND dds.diaDaSemana IN :diasDaSemana")
    List<Trajeto> findAllRecomendados(@Param("horaSaida")String horarioSaida, @Param("diasDaSemana")List<DiasDaSemana> diasDaSemana);

}
