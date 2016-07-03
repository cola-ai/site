
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.service.servicos.UsuarioServico;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/acess")
public class AcessController {

    @Autowired
    UsuarioServico usuarioServico;

    @RequestMapping(value = "/login")
    public String login(Model model) {
        return "acess/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/acess/login?logout";
    }

    @RequestMapping(value ="/esqueceuSenha", method = RequestMethod.GET )
    public String esqueceuSenha(Model model){
        model.addAttribute("usuario", new UsuarioViewModel());
        return "acess/esqueceuSenha";
    }
    
    @RequestMapping(value ="/esqueceuSenha", method = RequestMethod.POST )
    public String esqueceuSenha(UsuarioViewModel usuarioViewModel){
        usuarioViewModel =  usuarioServico.buscarPorEmail(usuarioViewModel.getEmail());
        if (usuarioViewModel != null) {
            usuarioServico.liberarAlterarSenha(usuarioViewModel);
            return "redirect:login?verifiqueEmail";
        }
        else
           return "redirect:/acess/esqueceuSenha?usuarioNaoEncontrado";
    }
    
    @RequestMapping(value = "/alterarSenha", method = RequestMethod.POST)
    public String alterarSenha(UsuarioViewModel usuario){
        if(usuario.getIdUsuario() != null ) {
            usuarioServico.alterarSenha(usuario);
            return "redirect:/acess/login?senhaAlterada";
        }
        
        return "redirect:/acess/login?usuarioNaoEncontrado";
    }
}
