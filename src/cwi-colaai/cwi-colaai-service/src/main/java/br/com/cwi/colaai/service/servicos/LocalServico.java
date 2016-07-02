
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Local;
import br.com.cwi.colaai.entity.view_model.LocalViewModel;
import br.com.cwi.colaai.service.repositorios.LocalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Érico de Souza Loewe
 */
@Service
public class LocalServico {
    
    @Autowired
    private LocalRepositorio localRepositorio;

    public Local salvar(LocalViewModel localViewModel) {
        Local local = localViewModel.toLocal();
        localRepositorio.save(local);
        return local;
    }
}
