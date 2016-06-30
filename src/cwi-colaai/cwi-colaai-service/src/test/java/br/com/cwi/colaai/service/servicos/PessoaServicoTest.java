package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Pessoa;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.service.repositorios.PessoaRepositorio;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Érico de Souza Loewe
 */
@RunWith(MockitoJUnitRunner.class)
public class PessoaServicoTest {

    @Mock
    PessoaRepositorio pessoaRepositorio;
    
    @InjectMocks
    PessoaServico pessoaServico;
    
    
    @Before
    public void setUp() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        doReturn(pessoa).when(pessoaRepositorio).save(any(Pessoa.class));
    }

    /**
     * Test of criar method, of class PessoaServico.
     */
    @Test
    public void testCriar() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        assertEquals("As pessoas não são iguais", pessoa.getId(), pessoaServico.criar(mock(UsuarioViewModel.class)).getId());
    }
    
}
