package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Pessoa;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.repositorios.PessoaRepositorio;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import br.com.cwi.colaai.service.servicos.PessoaServico;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    SocialUserDetailsService userDetailsService;

    @Mock
    PessoaRepositorio pessoaRepositorio;

    @InjectMocks
    UsuarioController cadastroController;
    
    @Mock
    PessoaServico pessoaServico;

    MockMvc mockMvc;

    @Before
    public void setUp() throws InstantiationException, IllegalAccessException {
        Pessoa pessoa = new Pessoa();
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setEstaAutorizado(true);
        usuario.setPessoa(pessoa);
        doReturn(mock(UsuarioViewModel.class)).when(usuarioServico).buscarPorEmail("email");
        doReturn(usuario).when(usuarioRepositorio).findOneByEmail("email");
        doReturn(usuario).when(usuarioRepositorio).findById(1l);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(cadastroController).setViewResolvers(viewResolver).build();
        
        
        {
            final InformacoesUsuarioAtual informacoesUsuarioAtual = mock(InformacoesUsuarioAtual.class);
            doReturn(informacoesUsuarioAtual).when(userDetailsService).getInformacoesUsuarioAtual();
            doReturn("email").when(informacoesUsuarioAtual).getEmail();
            
        }
        
    }

     @Test
    public void testConfiguracoes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/configuracoes"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/configuracoes"));
    }
    
    @Test
    public void testAlterarDadosCadastrais() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/alterarCadastro")
                .param("idUsuario", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:configuracoes?salvo"));
    }
    
    @Test
    public void testAlterarSenha_UsuarioViewModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/alterarSenha").param("senha", "novaSenha"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:configuracoes?salvo"));
    }
    
    @Test
    public void testCadastrar_Model_UsuarioViewModel() throws Exception {
        mockMvc.perform(get("/usuario/cadastrar"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/cadastrar"));
    }
  
}
