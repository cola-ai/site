/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.view_model.FiltroGrupoViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoParaListarViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
import br.com.cwi.colaai.entity.view_model.SolicitacaoViewModel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author diuly.barreto
 */
public class GrupoServicoTest {
    
    public GrupoServicoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of criarGrupo method, of class GrupoServico.
     */
    @Test
    public void testCriarGrupo() {
        System.out.println("criarGrupo");
        GrupoViewModel grupoViewModel = null;
        GrupoServico instance = new GrupoServico();
        instance.criarGrupo(grupoViewModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGruposDoUsuario method, of class GrupoServico.
     */
    @Test
    public void testGetGruposDoUsuario() {
        System.out.println("getGruposDoUsuario");
        Long usuarioId = null;
        GrupoServico instance = new GrupoServico();
        List<GrupoParaListarViewModel> expResult = null;
        List<GrupoParaListarViewModel> result = instance.getGruposDoUsuario(usuarioId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGruposUsuarioLidera method, of class GrupoServico.
     */
    @Test
    public void testGetGruposUsuarioLidera() {
        System.out.println("getGruposUsuarioLidera");
        Long usuarioId = null;
        GrupoServico instance = new GrupoServico();
        List<GrupoParaListarViewModel> expResult = null;
        List<GrupoParaListarViewModel> result = instance.getGruposUsuarioLidera(usuarioId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionarItinerariosAoGrupo method, of class GrupoServico.
     */
    @Test
    public void testAdicionarItinerariosAoGrupo() {
        System.out.println("adicionarItinerariosAoGrupo");
        List<Long> listaDeIds = null;
        Long id = null;
        GrupoServico instance = new GrupoServico();
        instance.adicionarItinerariosAoGrupo(listaDeIds, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerItinerariosdoGrupo method, of class GrupoServico.
     */
    @Test
    public void testRemoverItinerariosdoGrupo() {
        System.out.println("removerItinerariosdoGrupo");
        List<Long> listaDeIds = null;
        GrupoServico instance = new GrupoServico();
        instance.removerItinerariosdoGrupo(listaDeIds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGruposPorFiltro method, of class GrupoServico.
     */
    @Test
    public void testGetGruposPorFiltro() {
        System.out.println("getGruposPorFiltro");
        FiltroGrupoViewModel filtro = null;
        Long usuarioId = null;
        GrupoServico instance = new GrupoServico();
        List<GrupoParaListarViewModel> expResult = null;
        List<GrupoParaListarViewModel> result = instance.getGruposPorFiltro(filtro, usuarioId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enviarSolicitacao method, of class GrupoServico.
     */
    @Test
    public void testEnviarSolicitacao() {
        System.out.println("enviarSolicitacao");
        Long idGrupo = null;
        Long idUsuario = null;
        GrupoServico instance = new GrupoServico();
        instance.enviarSolicitacao(idGrupo, idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerSolicitacao method, of class GrupoServico.
     */
    @Test
    public void testRemoverSolicitacao() {
        System.out.println("removerSolicitacao");
        Long idGrupo = null;
        Long usuarioId = null;
        GrupoServico instance = new GrupoServico();
        instance.removerSolicitacao(idGrupo, usuarioId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerUsuarioDoGrupo method, of class GrupoServico.
     */
    @Test
    public void testRemoverUsuarioDoGrupo() {
        System.out.println("removerUsuarioDoGrupo");
        Long idGrupo = null;
        Long usuarioId = null;
        GrupoServico instance = new GrupoServico();
        instance.removerUsuarioDoGrupo(idGrupo, usuarioId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarMinhasSolicitacoes method, of class GrupoServico.
     */
    @Test
    public void testBuscarMinhasSolicitacoes() {
        System.out.println("buscarMinhasSolicitacoes");
        Long usuarioId = null;
        GrupoServico instance = new GrupoServico();
        List<SolicitacaoViewModel> expResult = null;
        List<SolicitacaoViewModel> result = instance.buscarMinhasSolicitacoes(usuarioId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGruposRecomendados method, of class GrupoServico.
     */
    @Test
    public void testGetGruposRecomendados() {
        System.out.println("getGruposRecomendados");
        Long usuarioId = null;
        GrupoServico instance = new GrupoServico();
        List<GrupoParaListarViewModel> expResult = null;
        List<GrupoParaListarViewModel> result = instance.getGruposRecomendados(usuarioId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerGrupo method, of class GrupoServico.
     */
    @Test
    public void testRemoverGrupo() {
        System.out.println("removerGrupo");
        Long idGrupo = null;
        GrupoServico instance = new GrupoServico();
        boolean expResult = false;
        boolean result = instance.removerGrupo(idGrupo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aceitarSolicitacao method, of class GrupoServico.
     */
    @Test
    public void testAceitarSolicitacao() {
        System.out.println("aceitarSolicitacao");
        Long idSolicitacao = null;
        GrupoServico instance = new GrupoServico();
        boolean expResult = false;
        boolean result = instance.aceitarSolicitacao(idSolicitacao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recusarSolicitacao method, of class GrupoServico.
     */
    @Test
    public void testRecusarSolicitacao() {
        System.out.println("recusarSolicitacao");
        Long idSolicitacao = null;
        GrupoServico instance = new GrupoServico();
        boolean expResult = false;
        boolean result = instance.recusarSolicitacao(idSolicitacao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
