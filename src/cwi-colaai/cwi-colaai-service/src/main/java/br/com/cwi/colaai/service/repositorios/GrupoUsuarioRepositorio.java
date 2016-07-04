
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.GrupoUsuario;
import br.com.cwi.colaai.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Alycio
 */
public interface GrupoUsuarioRepositorio extends CrudRepository<GrupoUsuario, Long> {
    
    GrupoUsuario findOneByGrupoAndUsuario(Grupo grupo, Usuario usuario);
}
