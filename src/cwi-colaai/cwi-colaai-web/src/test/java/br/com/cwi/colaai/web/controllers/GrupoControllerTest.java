/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.view_model.GrupoParaListarViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.servicos.GrupoServico;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 *
 * @author diuly.barreto
 */
@RunWith(MockitoJUnitRunner.class)
public class GrupoControllerTest {
    
    @InjectMocks
    GrupoController grupoController;
    
    MockMvc mockMvc;
    
    @Mock
    GrupoServico grupoServico;
    
    @Mock
    SocialUserDetailsService userDetailsService;
    
    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(grupoController).setViewResolvers(viewResolver).build();
        {
            final InformacoesUsuarioAtual informacoesUsuarioAtual = mock(InformacoesUsuarioAtual.class);
            doReturn(informacoesUsuarioAtual).when(userDetailsService).getInformacoesUsuarioAtual();
            doReturn(1l).when(informacoesUsuarioAtual).getUsuarioId();
            List<GrupoParaListarViewModel> lista = new ArrayList<>();
            lista.add(new GrupoParaListarViewModel(1l, 3, "Teste", null, null, null));
            doReturn(lista).when(grupoServico).getGruposUsuarioLidera(1l);
        }
    }
    
    /**
     * Test of criarGrupo method, of class GrupoController.
     */
    @Test
    public void testCriarGrupo_Model() throws Exception {
        
        mockMvc.perform(get("/grupo/criarGrupo"))
                .andExpect(model().attribute("listaDeGrupos", hasSize(1)))
                .andExpect(status().isOk())
                .andExpect(view().name("grupo/criarGrupo"));
    }

    /**
     * Test of criarGrupo method, of class GrupoController.
     */
    @Test
    public void testCriarGrupo_GrupoViewModel() {
        
    }

    /**
     * Test of adicionarUsuario method, of class GrupoController.
     */
    @Test
    public void testAdicionarUsuario() {
        
    }

    /**
     * Test of adicionarItinerarios method, of class GrupoController.
     */
    @Test
    public void testAdicionarItinerarios() {
        
    }

    /**
     * Test of removerItinerarios method, of class GrupoController.
     */
    @Test
    public void testRemoverItinerarios() {
        
    }

    /**
     * Test of removerGrupo method, of class GrupoController.
     */
    @Test
    public void testRemoverGrupo() {
        
    }

    /**
     * Test of administrarGrupo method, of class GrupoController.
     */
    @Test
    public void testAdministrarGrupo() {
        
    }

    /**
     * Test of pesquisar method, of class GrupoController.
     */
    @Test
    public void testPesquisar() {
        
    }
    
}
