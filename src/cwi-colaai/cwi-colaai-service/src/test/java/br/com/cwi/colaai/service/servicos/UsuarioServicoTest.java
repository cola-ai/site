
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Pessoa;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Érico de Souza Loewe
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioServicoTest {
    
    @InjectMocks
    UsuarioServico usuarioServico;
    
    @Mock
    UsuarioRepositorio usuarioRepositorio;
    
    @Mock
    PessoaServico pessoaServico;
    
    @Mock
    TokenServico tokenServico;
    
    @Before
    public void setUp() {
        final String EMAIL = "um_email";
        final Pessoa pessoa = mock(Pessoa.class);
        final Usuario usuario = mock(Usuario.class);
        final UsuarioViewModel usuarioViewModel = mock(UsuarioViewModel.class);        
        
        doReturn(pessoa).when(usuario).getPessoa();
        doReturn(usuarioViewModel).when(usuario).toUsuarioViewModel();
        doReturn(usuario).when(usuarioRepositorio).findOneByEmail(EMAIL);
        doReturn(usuario).when(usuarioRepositorio).findOneByEmailAndEstaAutorizadoTrue(EMAIL);        
    }

    /**
     * Test of emailExiste method, of class UsuarioServico.
     */
    @Test()
    public void emailExisteComUmEmailExistenteDeveRetornarTrue() {
        final String EMAIL = "um_email";
        boolean esperado = true, obtido;
        
        obtido = usuarioServico.emailExiste(EMAIL);
        
        assertEquals(esperado, obtido);
    }
    
    /**
     * Test of emailExiste method, of class UsuarioServico.
     */
    @Test()
    public void emailExisteComUmEmailInexistenteDeveRetornarFalse() {
        final String EMAIL = "outro_email";
        boolean esperado = false, obtido;
        
        obtido = usuarioServico.emailExiste(EMAIL);
        
        assertEquals(esperado, obtido);
    }
    
    /**
     * Test of emailExiste method, of class UsuarioServico.
     */
    @Test()
    public void emailExisteComNullDeveRetornarFalse() {
        final String EMAIL = null;
        boolean esperado = false, obtido;
        
        obtido = usuarioServico.emailExiste(EMAIL);
        
        assertEquals(esperado, obtido);
    }
    
    /**
     * Test of buscarAutorizadoPorEmail method, of class UsuarioServico.
     */
    @Test()
    public void buscarAutorizadoPorEmailComUmEmailExistenteDeveRetornarUmUsuario() {
        final String EMAIL = "um_email";
        UsuarioViewModel obtido;
        
        obtido = usuarioServico.buscarAutorizadoPorEmail(EMAIL);
        
        assertNotNull(obtido);
    }
    
    /**
     * Test of buscarAutorizadoPorEmail method, of class UsuarioServico.
     */
    @Test()
    public void buscarAutorizadoPorEmailComUmEmailInexistenteDeveRetornarNull() {
        final String EMAIL = "outro_email";
        UsuarioViewModel obtido;
        
        obtido = usuarioServico.buscarAutorizadoPorEmail(EMAIL);
        
        assertNull(obtido);
    }
    
    /**
     * Test of buscarAutorizadoPorEmail method, of class UsuarioServico.
     */
    @Test()
    public void buscarAutorizadoPorEmailComNullDeveRetornarNull() {
        final String EMAIL = null;
        UsuarioViewModel obtido;
        
        obtido = usuarioServico.buscarAutorizadoPorEmail(EMAIL);
        
        assertNull(obtido);
    }
    
    /**
     * Test of buscarPorEmail method, of class UsuarioServico.
     */
    @Test()
    public void buscarPorEmailComUmEmailExistenteDeveRetornarUmUsuario() {
        final String EMAIL = "um_email";
        UsuarioViewModel obtido;
        
        obtido = usuarioServico.buscarPorEmail(EMAIL);
        
        assertNotNull(obtido);
    }
    
    /**
     * Test of buscarPorEmail method, of class UsuarioServico.
     */
    @Test()
    public void buscarPorEmailComUmEmailInexistenteDeveRetornarNull() {
        final String EMAIL = "outro_email";
        UsuarioViewModel obtido;
        
        obtido = usuarioServico.buscarPorEmail(EMAIL);
        
        assertNull(obtido);
    }
    
    /**
     * Test of buscarPorEmail method, of class UsuarioServico.
     */
    @Test()
    public void buscarPorEmailComNullDeveRetornarNull() {
        final String EMAIL = null;
        UsuarioViewModel obtido;
        
        obtido = usuarioServico.buscarPorEmail(EMAIL);
        
        assertNull(obtido);
    }
    
    /**
     * Test of criar method, of class UsuarioServico.
     */
    @Test()
    public void criarUmUsuarioComEmailExistenteDeveRetornarFalse() {
        final UsuarioViewModel usuario = mock(UsuarioViewModel.class);
        final ImagemViewModel imagem = mock(ImagemViewModel.class);
        usuario.setEmail("um_email");
        boolean esperado = false, obtido;
        
        obtido = usuarioServico.criar(usuario, imagem);
        
        assertEquals(esperado, obtido);
    }
}
