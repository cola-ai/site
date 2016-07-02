
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.PassoDeRota;
import br.com.cwi.colaai.entity.Rota;
import br.com.cwi.colaai.entity.view_model.PassoDeRotaViewModel;
import br.com.cwi.colaai.service.repositorios.PassoDeRotaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Érico de Souza Loewe
 */
@Service
public class PassoDeRotaServico {
    
    @Autowired
    private PassoDeRotaRepositorio passoDeRotaRepositorio;
    
    @Autowired
    private TrajetoServico trajetoServico;

    public void salvarPassosDaRota(List<PassoDeRotaViewModel> passosDeRotaViewModels, Rota rota) {
        passosDeRotaViewModels.forEach((p) -> {
            PassoDeRota novoPasso = p.toPassoDeRota(rota);
            passoDeRotaRepositorio.save(novoPasso);
            
            trajetoServico.salvarTrajetosDoPassoDeRota(p.getLatitudes_longitudes(), novoPasso);
        });
    }
}
