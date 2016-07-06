
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Geolocalizacao;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Érico de Souza Loewe
 */
@RunWith(MockitoJUnitRunner.class)
public class GeocalizacaoServicoTest {
    
    @InjectMocks
    GeocalizacaoServico geocalizacaoServico;
    
    /**
     * Test of getValorAbsoluto method, of class GeocalizacaoServico.
     */
    @Test
    public void pegarValorAbslutoDeZeroDeveRetornarZero() {
        Double esperado = 0d, obtido;
        Geolocalizacao geo1 = new Geolocalizacao(0d, 0d);
        Geolocalizacao geo2 = new Geolocalizacao(0d, 0d);
        
        obtido = geocalizacaoServico.getValorAbsoluto(geo1, geo2);
        
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void pegarValorAbslutoDeDezECincoDeveRetornarDez() {
        Double esperado = 10d, obtido;
        Geolocalizacao geo1 = new Geolocalizacao(10d, 5d);
        Geolocalizacao geo2 = new Geolocalizacao(5d, 10d);
        
        obtido = geocalizacaoServico.getValorAbsoluto(geo1, geo2);
        
        assertEquals(esperado, obtido);
    }
    
    @Test(expected = NullPointerException.class)
    public void pegarValorAbslutoDeNullDeveRetornarUmaExcecao() {
        Double esperado = null, obtido;
        
        obtido = geocalizacaoServico.getValorAbsoluto(null, null);
        
        assertEquals(esperado, obtido);
    }
}
