
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Alycio
 */


public interface GrupoRepositorio extends JpaRepository<Grupo, Long>, JpaSpecificationExecutor<Grupo> {
    
    List<Grupo> findAllByLider(Usuario lider);
    List<Grupo> findByNomeContainingIgnoreCaseAndLider_IdNot(String nome, Long id);    
    List<Grupo> findByItinerariosInAndLider_IdNotAndNomeContainingIgnoreCase(List<Itinerario> itinerarios, Long id, String nome);
}
