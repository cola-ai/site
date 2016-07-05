
package br.com.cwi.colaai.web.controllers.rest;

import br.com.cwi.colaai.entity.view_model.GrupoParaListarViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.servicos.GrupoServico;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author Érico de Souza Loewe
 */
@RunWith(MockitoJUnitRunner.class)
public class GrupoRestController {
    
    @InjectMocks
    GrupoRestController grupoController;
    
    MockMvc mockMvc;
    
    @Mock
    GrupoServico grupoServico;
    
    @Mock
    SocialUserDetailsService userDetailsService;
    
    @Before
    public void setUp() {        
        mockMvc = MockMvcBuilders.standaloneSetup(grupoController).build();
    }
    
//    @Test
//    public void enviarSolicitacaoDeveEnviarUmaSolicitacao() throws Exception {
//        mockMvc.perform(post("/rest/grupo/enviarSolicitacao").param("idGrupo", "1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0]", is("Grupo solicitado com sucesso")));
//    }
}
