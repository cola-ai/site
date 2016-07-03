package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.service.servicos.TokenServico;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author Érico de Souza Loewe
 */
@RunWith(MockitoJUnitRunner.class)
public class TokenControllerTest {

    @Mock
    TokenServico tokenServico;

    @Mock
    UsuarioServico usuarioServico;

    @InjectMocks
    TokenController tokenController;

    @Mock
    Token token;

    @Mock
    Usuario usuario;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        doReturn(null).when(tokenServico).buscarPorValorToken("2");
        doReturn(token).when(tokenServico).buscarPorValorToken("1");
        doReturn(usuario).when(token).getUsuario();


        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(tokenController).setViewResolvers(viewResolver).build();
    }

    /**
     * Test of confirma method, of class TokenController.
     */
    @Test
    public void testConfirma() throws Exception {
        mockMvc.perform(get("/token/confirma").param("valor", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/acess/login?tokenAprovado"));
    }

    /**
     * Test of recuperarSenha method, of class TokenController.
     */
    @Test
    public void testRecuperarSenha() throws Exception {
        mockMvc.perform(get("/token/recuperarSenha").param("valor", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("token/recuperarSenha"));
        mockMvc.perform(get("/token/recuperarSenha").param("valor", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/acess/esqueceuSenha?usuarioNaoEncontrado"));
    }
}
