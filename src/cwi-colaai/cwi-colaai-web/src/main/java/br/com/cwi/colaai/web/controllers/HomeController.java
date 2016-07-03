package br.com.cwi.colaai.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Érico de Souza Loewe
 */
@Controller
public class HomeController {
    
    
    
    @RequestMapping(value = "/")
    String index(Model model) {
        
        return "home/index";
    }
}