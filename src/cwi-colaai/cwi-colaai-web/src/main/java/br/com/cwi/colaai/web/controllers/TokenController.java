package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.service.servicos.TokenServico;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String confirma(@RequestParam String valor) {
        
        Token token = tokenServico.buscarPorValorToken(valor);
        tokenServico.aprovarToken(token);
        
        usuarioServico.autorizarUsuario(token.getUsuario());
        return "redirect:/login?tokenAprovado";
    }
    
    @RequestMapping(value = "/EsqueceuSenha")
    public String recuperacaoDeSenha(@RequestParam String valor) {
        //TODO Confirmação de Senha
        Token token = tokenServico.buscarPorValorToken(valor);
        
        usuarioServico.autorizarUsuario(token.getUsuario());
        return "redirect:/login";
    }
}
