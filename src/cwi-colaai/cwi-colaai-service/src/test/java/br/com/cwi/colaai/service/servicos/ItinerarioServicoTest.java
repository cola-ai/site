
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.DiasDaSemana;
import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.service.repositorios.ItinerarioDiasDaSemanaRepositorio;
import br.com.cwi.colaai.service.repositorios.ItinerarioRepositorio;
import br.com.cwi.colaai.service.repositorios.TrajetoRepositorio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;

/**
 *
 * @author Érico de Souza Loewe
 */
public class ItinerarioServicoTest {
    
    @Mock
    private ItinerarioRepositorio itinerarioRepositorio;
    
    @Mock
    private TrajetoRepositorio trajetoRepositorio;
    
    @Mock
    private ItinerarioDiasDaSemanaRepositorio itinerarioDiasDaSemanaRepositorio;
    
    @Mock
    private RotaServico rotaServico;
    
    @Mock
    private LocalServico localServico;
    
    @Mock
    private UsuarioServico usuarioServico;
    
    @InjectMocks
    private ItinerarioServico itinerarioServico;
    
    @Before
    public void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setEmail("email");
        doReturn(usuario).when(usuarioServico).buscarPorId(1l);
        doReturn(null).when(trajetoRepositorio).findAllRecomendados("10:00", new ArrayList<>(Arrays.asList(DiasDaSemana.SEGUNDA)), usuario);
        doReturn(null).when(trajetoRepositorio).findAllPorUsuario(usuario);
    }

    /**
     * Test of getItinerariosRelacionados method, of class ItinerarioServico.
     */
    @Test
    public void testGetItinerariosRelacionados() {
        List<Itinerario> expResult = null;
        List<Itinerario> result = itinerarioServico.getItinerariosRelacionados(1l);
        assertEquals(expResult, result);
    }    
}
