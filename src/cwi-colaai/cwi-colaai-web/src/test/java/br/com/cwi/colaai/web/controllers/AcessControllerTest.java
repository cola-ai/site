/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import javax.servlet.http.HttpSession;
import static org.apache.http.client.methods.RequestBuilder.post;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author diuly.barreto
 */
@RunWith(MockitoJUnitRunner.class)
public class AcessControllerTest {
    
    @Mock
    UsuarioServico usuarioServico;
    
    @Mock
    Usuario usuario;
    
    @InjectMocks
    AcessController acessController;
    
    MockMvc mockMvc;
    
    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        
        mockMvc = MockMvcBuilders.standaloneSetup(acessController).setViewResolvers(viewResolver).build();
    }
    
    /**
     * Test of login method, of class AcessController.
     */
    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * Test of logout method, of class AcessController.
     */
    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?logout"));
    }

    /**
     * Test of esqueceuSenha method, of class AcessController.
     */
//    @Test
//    public void testEsqueceuSenha_Model() throws Exception {
//        mockMvc.perform(get("/esqueceuSenha"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("esqueceuSenha"));
//    }

}
