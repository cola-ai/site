/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.StatusToken;
import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.service.repositorios.TokenRepositorio;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author diuly.barreto
 */
@RunWith(MockitoJUnitRunner.class)
public class TokenServicoTest {
    
    @Mock
    TokenRepositorio tokenRepositorio;
    
    @InjectMocks
    TokenServico tokenServico;
    
    @Before
    public void setUp() {
        final Token token = new Token();
        token.setId(1l);
        token.setValor("valor");
        token.setStatus(StatusToken.PENDENTE);
        doReturn(token).when(tokenRepositorio).save(any(Token.class));
        doReturn(token).when(tokenRepositorio).findByValor("valor");
    }
   
    @Test
    public void testCriarTokenComUsuarioNulo() {
        Token token = tokenServico.criarToken(null);
        assertNull(token);
    }
    
    @Test
    public void testCriarTokenComUsuarioValido() {
        Token token = new Token();
        token.setId(1l);
        
        assertEquals(token.getId(), tokenServico.criarToken(mock(Usuario.class)).getId());
    }
    
    /**
     * Test of buscarPorValorToken method, of class TokenServico.
     */
    @Test
    public void testBuscarPorValorToken() {
        Token token = new Token();
        token.setId(1l);
        token.setValor("valor");
        assertEquals("Retornou o token", token.getId(), tokenServico.buscarPorValorToken("valor").getId());
    }
    
}
