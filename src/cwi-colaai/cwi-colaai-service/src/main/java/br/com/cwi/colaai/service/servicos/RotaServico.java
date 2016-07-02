
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Rota;
import br.com.cwi.colaai.entity.view_model.PassoDeRotaViewModel;
import br.com.cwi.colaai.entity.view_model.RotaViewModel;
import br.com.cwi.colaai.service.repositorios.RotaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Érico de Souza Loewe
 */
@Service
public class RotaServico {
    
    @Autowired
    private RotaRepositorio rotaRepositorio;
    
    @Autowired
    private PassoDeRotaServico passoDeRotaServico;
    
    public Rota salvar(RotaViewModel rotaViewModel, List<PassoDeRotaViewModel> passosDeRotaViewModels) {   
        Rota rota = rotaViewModel.toRota();
        rotaRepositorio.save(rota);
        
        passoDeRotaServico.salvarPassosDaRota(passosDeRotaViewModels, rota);
        return rota;
    }
}
