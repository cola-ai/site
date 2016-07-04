
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Solicitacao;
import br.com.cwi.colaai.entity.StatusSolicitacao;
import br.com.cwi.colaai.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Érico de Souza Loewe
 */
public interface SolicitacaoRepositorio extends CrudRepository<Solicitacao, Long> {
    
    Solicitacao findOneByUsuarioAndGrupoSolicitadoAndStatus(Usuario usuario, Grupo grupoSolicitado, StatusSolicitacao status);
}
