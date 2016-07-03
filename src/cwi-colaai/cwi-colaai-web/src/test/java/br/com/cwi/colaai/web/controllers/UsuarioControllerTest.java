
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Pessoa;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.service.repositorios.PessoaRepositorio;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author Diuly
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTest {
    
    @Mock
    UsuarioServico usuarioServico;
    
    @Mock
    UsuarioRepositorio usuarioRepositorio;
    
    @Mock
    PessoaRepositorio pessoaRepositorio;
    
    @InjectMocks
    UsuarioController cadastroController;
    
    MockMvc mockMvc;
    
    @Before
    public void setUp() throws InstantiationException, IllegalAccessException {
        Pessoa pessoa = new Pessoa();
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setEstaAutorizado(true);
        usuario.setPessoa(pessoa);
        doReturn(usuario).when(usuarioRepositorio).findById(1l);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(cadastroController).setViewResolvers(viewResolver).build();
    }
   
    /**
     * Test of cadastrar method, of class CadastroController.
     */
    @Test
    public void testCadastrar_Model_UsuarioViewModel() throws Exception {
        mockMvc.perform(get("/usuario/cadastrar"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/cadastrar"));
    }
    
    @Test
    public void testAlterarSenha_UsuarioViewModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/alterarSenha").param("senha", "novaSenha"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:configuracoes?salvo"));
    }
    
}
