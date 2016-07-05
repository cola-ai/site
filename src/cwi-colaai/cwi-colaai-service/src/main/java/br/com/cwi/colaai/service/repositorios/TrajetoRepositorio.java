
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.DiasDaSemana;
import br.com.cwi.colaai.entity.Trajeto;
import br.com.cwi.colaai.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Érico de Souza Loewe
 */
public interface TrajetoRepositorio extends CrudRepository<Trajeto, Long> {
    
    @Query("SELECT t FROM Trajeto t JOIN FETCH t.localizacao JOIN t.passo p "
            + "JOIN p.rota r JOIN r.itinerarios i JOIN i.diasDaSemana dds "
            + "WHERE i.horarioSaida = :horaSaida AND dds.diaDaSemana IN :diasDaSemana AND i.usuario <> :usuario")
    List<Trajeto> findAllRecomendados(@Param("horaSaida")String horarioSaida, @Param("diasDaSemana")List<DiasDaSemana> diasDaSemana, @Param("usuario")Usuario usuario);
    
    @Query("SELECT t FROM Trajeto t JOIN FETCH t.localizacao JOIN t.passo p JOIN p.rota r JOIN r.itinerarios i "
            + "WHERE i.usuario = :usuario")
    List<Trajeto> findAllPorUsuario(@Param("usuario")Usuario usuario);
}
