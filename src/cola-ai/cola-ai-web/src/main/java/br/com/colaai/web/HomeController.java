package br.com.colaai.web;

import java.security.Principal;
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
    String toIndex(Model model, Principal principal) {
        String user = principal.getName();
        return "home/index";
    }
}