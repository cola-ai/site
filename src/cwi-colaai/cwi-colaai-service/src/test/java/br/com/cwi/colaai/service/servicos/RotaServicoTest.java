/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Rota;
import br.com.cwi.colaai.entity.view_model.PassoDeRotaViewModel;
import br.com.cwi.colaai.entity.view_model.RotaViewModel;
import br.com.cwi.colaai.service.repositorios.GrupoRepositorio;
import br.com.cwi.colaai.service.repositorios.RotaRepositorio;
import java.util.ArrayList;
import java.util.List;
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
public class RotaServicoTest {
    
    @Mock
    RotaRepositorio rotaRepositorio;
    
    @InjectMocks
    RotaServico rotaServico;
    
    @Mock
    PassoDeRotaServico passoDeRotaServico;
    
    @Mock
    RotaViewModel rotaViewModel;
    
    @Before
    public void setUp() {
        {
            Rota rota = new Rota();
            rota.setId(1l);
            doReturn(rota).when(rotaRepositorio).save(any(Rota.class));
        }
    }
    
    /**
     * Test of salvar method, of class RotaServico.
     */
    @Test
    public void testSalvar() {
        RotaViewModel rota = mock(RotaViewModel.class);
        List<PassoDeRotaViewModel> passos = new ArrayList<>();
        passos.add(new PassoDeRotaViewModel());
        passos.add(new PassoDeRotaViewModel());
        Rota rota2 = rotaServico.salvar(rota, passos);
        
        assertEquals(1l, rota2.getId().longValue());
    }
    
}
