package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.service.servicos.ServicoUsuario;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Érico de Souza Loewe
 */
@Controller
public class AcessController {

    @Autowired
    private ServicoUsuario _servicoUsuario;
    
    @RequestMapping(value="/login")
    public String login() {
        return "login";
    } 
    
    @RequestMapping(value="/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    } 
    
    @RequestMapping(value="/cadastrar")
    public String cadastrar() {
        return "cadastrar";
    }
    
    @RequestMapping(value="/salvar")
    public String cadastrar(UsuarioViewModel usuario) {
        _servicoUsuario.criar(usuario);
        return "login";
    }
}
