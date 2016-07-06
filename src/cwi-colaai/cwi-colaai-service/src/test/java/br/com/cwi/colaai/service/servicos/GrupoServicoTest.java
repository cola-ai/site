
package br.com.cwi.colaai.service.servicos;


import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.GrupoUsuario;
import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.Solicitacao;
import br.com.cwi.colaai.service.repositorios.GrupoRepositorio;
import br.com.cwi.colaai.service.repositorios.SolicitacaoRepositorio;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author diuly.barreto
 */
@RunWith(MockitoJUnitRunner.class)
public class GrupoServicoTest {
    
    @Mock
    GrupoRepositorio grupoRepositorio;
    
    @InjectMocks
    GrupoServico grupoServico;
    
    @Mock
    SolicitacaoRepositorio solicitacaoRepositorio;
    
    @Mock
    GrupoUsuarioServico grupoUsuarioServico;
    
    @Before
    public void setUp() {
        {
            Grupo grupo = mock(Grupo.class);
            grupo.setItinerarios(new ArrayList<Itinerario>());
            grupo.setUsuarios(new ArrayList<GrupoUsuario>());
            doReturn(grupo).when(grupoRepositorio).findOne(1l);
        }
            
        {
            Grupo grupoComUsuario = mock(Grupo.class);
            GrupoUsuario g = mock(GrupoUsuario.class);
            List<GrupoUsuario> lista = mock(ArrayList.class);
            lista.add(g);
            grupoComUsuario.setUsuarios(lista);
            Itinerario i = mock(Itinerario.class);
            List<Itinerario> itinerarios = mock(ArrayList.class);
            itinerarios.add(i);
            grupoComUsuario.setItinerarios(itinerarios);
            doReturn(grupoComUsuario).when(grupoRepositorio).findOne(2l);
            doReturn(lista).when(grupoComUsuario).getUsuarios();
            doReturn(itinerarios).when(grupoComUsuario).getItinerarios();  
        }
        
        {
            doReturn(null).when(solicitacaoRepositorio).findOne(2l);
        }
        
        {
            Solicitacao solicitacao = mock(Solicitacao.class);
            doReturn(solicitacao).when(solicitacaoRepositorio).findOne(1l);
            doReturn(solicitacao).when(solicitacaoRepositorio).save(Matchers.any(Solicitacao.class));
        }
    }

    /**
     * Test of removerGrupo method, of class GrupoServico.
     */
    @Test
    public void testRemoverGrupo() {
       assertTrue(grupoServico.removerGrupo(1l));
    }
    
    @Test
    public void testRemoverGrupoComUsuario() {
       assertFalse(grupoServico.removerGrupo(2l));
    }
    
    @Test
    public void testAceitarSolicitacaoComSolicitacaoNula() {
        assertFalse(grupoServico.aceitarSolicitacao(2l));
    }

    /**
     * Test of recusarSolicitacao method, of class GrupoServico.
     */
    @Test
    public void testRecusarSolicitacao() {
        assertTrue(grupoServico.recusarSolicitacao(1l));
    }
    
}
