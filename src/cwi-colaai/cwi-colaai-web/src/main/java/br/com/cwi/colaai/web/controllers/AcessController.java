package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.service.servicos.ServicoUsuario;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Érico de Souza Loewe
 */
@Controller
public class AcessController {

    private ServicoUsuario _servicoUsuario = new ServicoUsuario();
    
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
    public String cadastrar(Model model) {
        model.addAttribute("usuario", new UsuarioViewModel());
        return "cadastrar";
    }
    
    @RequestMapping(value="/salvar")
    public String cadastrar(UsuarioViewModel usuario) {
        _servicoUsuario.criar(usuario);
        return "login";
    }
}
