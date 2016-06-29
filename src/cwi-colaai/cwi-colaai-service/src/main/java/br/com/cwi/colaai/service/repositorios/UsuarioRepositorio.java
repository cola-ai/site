package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Érico de Souza Loewe
 */
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
    
    Usuario findOneByEmail(String email);
    
    Usuario findById(Long id);

    Usuario findOneByEmailAndEstaAutorizadoTrue(String email);

}
