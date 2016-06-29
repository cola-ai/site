
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AcessController {

    private static final Logger LOGGER = Logger.getLogger(AcessController.class.getName());
    
    @Autowired
    UsuarioServico _servicoUsuario;

    @RequestMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
    public String cadastrar(Model model) {
        model.addAttribute("usuario", new UsuarioViewModel());
        return "cadastrar";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String cadastrar(UsuarioViewModel usuario, MultipartFile file) {
        
        try {
            _servicoUsuario.criar(usuario, new ImagemViewModel(file.getName(), file.getOriginalFilename(), file.getInputStream()));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/login?cadastro";
    }
    
    @RequestMapping(value ="/esqueceuSenha", method = RequestMethod.GET )
    public String esqueceuSenha(Model model){
        model.addAttribute("usuario", new UsuarioViewModel());
        return "esqueceuSenha";
    }
     @RequestMapping(value ="/esqueceuSenha", method = RequestMethod.POST )
    public String esqueceuSenha(UsuarioViewModel usuarioViewModel){
        usuarioViewModel =  _servicoUsuario.buscarPorEmail(usuarioViewModel.getEmail());
        if (usuarioViewModel != null) {
            _servicoUsuario.liberarAlterarSenha(usuarioViewModel);
            return "redirect:login?verifiqueEmail";
        }
        else
           return "esqueceuSenha?usuarioNaoEncontrado";
    }
    
    @RequestMapping(value = "/alterarSenha", method = RequestMethod.POST)
    public String alterarSenha(UsuarioViewModel usuario){
        if(usuario.getIdUsuario() > 0){
        _servicoUsuario.alterarSenha(usuario);
        return "redirect:/login?senhaAlterada";
        }
        return "redirect:/login?usuarioNaoEncontrado";
    }
}
