
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.PassoDeRota;
import br.com.cwi.colaai.entity.Trajeto;
import br.com.cwi.colaai.entity.view_model.GeolocalizacaoViewModel;
import br.com.cwi.colaai.service.repositorios.TrajetoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Érico de Souza Loewe
 */
@Service
public class TrajetoServico {
    
    @Autowired
    private TrajetoRepositorio trajetoRepositorio;

    public void salvarTrajetosDoPassoDeRota(List<GeolocalizacaoViewModel> latitudes_longitudes, PassoDeRota novoPasso) {
        latitudes_longitudes.forEach((t) -> {
            Trajeto novoTrajeto = new Trajeto(t.toGeolocalizacao(), novoPasso);
            trajetoRepositorio.save(novoTrajeto);
        });
    }
}
