/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
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
public class CadastroControllerTest {
    @InjectMocks
    CadastroController cadastroController;
    
    MockMvc mockMvc;
    
    @Before
    public void setUp() throws InstantiationException, IllegalAccessException {
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
        mockMvc.perform(get("/cadastrar"))
                .andExpect(status().isOk())
                .andExpect(view().name("cadastrar"));
    }

}
