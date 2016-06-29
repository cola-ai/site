/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.service.servicos.TokenServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author diuly.barreto
 */

@Controller
public class TokenController {
    
    @Autowired
    TokenServico tokenServico;
    
    @RequestMapping(value = "/confirma")
    public String login(String valor) {
        
        Token token = tokenServico.BuscarPorValorToken(valor);
        // TODO - continuar fazendo a controller
        return "redirect:/login";
    }
}
