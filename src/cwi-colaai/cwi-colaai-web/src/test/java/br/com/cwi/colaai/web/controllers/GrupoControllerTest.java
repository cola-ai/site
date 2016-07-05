/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;


import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.GrupoParaListarViewModel;
import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.repositorios.GrupoRepositorio;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import br.com.cwi.colaai.service.servicos.GrupoServico;
import br.com.cwi.colaai.service.servicos.GrupoUsuarioServico;
import br.com.cwi.colaai.service.servicos.ItinerarioServico;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
    
    @Mock
    UsuarioRepositorio usuarioRepositorio;
    
    MockMvc mockMvc;
    
    @Mock
    GrupoServico grupoServico;
    
    @Mock
    GrupoUsuarioServico grupoUsuarioServico;
    
    @Mock
    SocialUserDetailsService userDetailsService;
    
    @Mock
    GrupoRepositorio grupoRepositorio;
    
    @Mock
    ItinerarioServico itinerarioServico;
    
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
        
        {
            Usuario user = new Usuario();
            doReturn(user).when(usuarioRepositorio).findById(1l);
        }
        
        {
            doReturn(mock(Grupo.class)).when(grupoRepositorio).findOne(1l);
        }
        
        {
            doReturn(true).when(grupoServico).removerGrupo(1l);
        }
        {
            doReturn(false).when(grupoServico).removerGrupo(2l);
        }
        
        {
            List<ItinerarioViewModel> lista = new ArrayList<>();
            lista.add(mock(ItinerarioViewModel.class));
            doReturn(lista).when(itinerarioServico).buscarItinerariosDoUsuario(1l);
            doReturn(lista).when(itinerarioServico).buscarItinerariosDoGrupo(1l);
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
    public void testCriarGrupo_GrupoViewModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupo/criarGrupo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:criarGrupo"));
    }

    /**
     * Test of adicionarUsuario method, of class GrupoController.
     */
    @Test
    public void testAdicionarUsuario() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupo/adicionarUsuario")
        .param("idUsuario", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:grupo/criarUsuario?usuarioAdicionado"));
    }
    
    @Test
    public void testAdicionarUsuario_UsuarioInvalido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupo/adicionarUsuario"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:grupo/criarGrupo"));
    }

    /**
     * Test of adicionarItinerarios method, of class GrupoController.
     */
    @Test
    public void testAdicionarItinerarios() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupo/adicionarItinerario")
        .param("id", "1").param("idItinerario", "1").param("idItinerario", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/grupo/administrarGrupo?id=1"));
    }

    /**
     * Test of removerItinerarios method, of class GrupoController.
     */
    @Test
    public void testRemoverItinerarios() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupo/removerItinerario")
        .param("id", "1").param("idItinerario", "1").param("idItinerario", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/grupo/administrarGrupo?id=1"));
    }

    /**
     * Test of removerGrupo method, of class GrupoController.
     */
    @Test
    public void testRemoverGrupo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupo/removerGrupo")
        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/grupo/criarGrupo?removido"));
    }
    
     @Test
    public void testRemoverGrupo_IdInvalido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupo/removerGrupo")
        .param("id", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/grupo/criarGrupo?erro"));
    }

    /**
     * Test of administrarGrupo method, of class GrupoController.
     */
    @Test
    public void testAdministrarGrupo() throws Exception {
        mockMvc.perform(get("/grupo/administrarGrupo")
        .param("id", "1"))
                .andExpect(model().attribute("id", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("grupo/administrarGrupo"));
    }

    /**
     * Test of pesquisar method, of class GrupoController.
     */
    @Test
    public void testPesquisar() throws Exception {
        mockMvc.perform(get("/grupo/pesquisar"))
                .andExpect(status().isOk())
                .andExpect(view().name("grupo/pesquisar"));
    }
    
}
