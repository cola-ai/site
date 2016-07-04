
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Solicitacao;
import br.com.cwi.colaai.entity.StatusSolicitacao;
import br.com.cwi.colaai.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Érico de Souza Loewe
 */
public interface SolicitacaoRepositorio extends CrudRepository<Solicitacao, Long> {
    
    Solicitacao findOneByUsuarioAndGrupoSolicitadoAndStatus(Usuario usuario, Grupo grupoSolicitado, StatusSolicitacao status);
    
    @Query("SELECT s FROM Solicitacao s JOIN s.grupoSolicitado g WHERE s.status = :status AND g.lider = :lider")
    List<Solicitacao> findAllByStatusSolicitacaoAndLider(@Param("status")StatusSolicitacao status,@Param("lider") Usuario lider);
}
