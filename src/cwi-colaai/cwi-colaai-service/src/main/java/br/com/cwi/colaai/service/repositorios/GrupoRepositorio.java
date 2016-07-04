
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Grupo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Alycio
 */


public interface GrupoRepositorio extends JpaRepository<Grupo, Long>, JpaSpecificationExecutor<Grupo> {

}
