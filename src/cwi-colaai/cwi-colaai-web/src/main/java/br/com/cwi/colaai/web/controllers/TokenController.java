package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.service.servicos.TokenServico;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author diuly.barreto
 */

@Controller
public class TokenController {
    
    @Autowired
    TokenServico tokenServico;
    
    @Autowired
    UsuarioServico usuarioServico;
    
    @RequestMapping(value = "/confirma")
    public String login(@RequestParam String valor) {
        
        Token token = tokenServico.buscarPorValorToken(valor);
        tokenServico.aprovarToken(token);
        
        usuarioServico.autorizarUsuario(token.getUsuario());
        return "redirect:/login?tokenAprovado";
    }
    
    @RequestMapping(value = "/recuperarSenha")
    public String recuperarSenha(@RequestParam String valor, Model model) {
        //TODO Validar Token Encontrado
        Token token = tokenServico.buscarPorValorToken(valor);
        UsuarioViewModel usuario = token.getUsuario().toUsuarioViewModel();
        model.addAttribute("usuario",usuario);
        return "recuperarSenha";
    }
    
    @RequestMapping(value ="esqueceuSenha" )
    public String esqueceuSenha(Model model){
        model.addAttribute("usuario", new UsuarioViewModel());
        return "esqueceuSenha";
    }
}
