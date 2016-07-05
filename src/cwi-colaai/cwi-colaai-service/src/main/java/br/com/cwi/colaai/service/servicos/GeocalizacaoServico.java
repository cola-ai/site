
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Geolocalizacao;
import org.springframework.stereotype.Service;

/**
 *
 * @author Érico de Souza Loewe
 */
@Service
public class GeocalizacaoServico {

    public Double getValorAbsoluto(Geolocalizacao localizacao, Geolocalizacao localizacao1) {
        Double dX = localizacao.getLatitude() - localizacao1.getLatitude();
        Double dY = localizacao.getLongitude() - localizacao1.getLongitude();
        return Math.abs(dX) + Math.abs(dY);
    }    
}
