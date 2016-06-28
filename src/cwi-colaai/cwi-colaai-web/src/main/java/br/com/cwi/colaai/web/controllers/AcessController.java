package br.com.cwi.colaai.web.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.cwi.colaai.service.servicos.UsuarioServico;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Érico de Souza Loewe
 */
@Controller
public class AcessController {

    private UsuarioServico _servicoUsuario = new UsuarioServico();

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    }

    @RequestMapping(value = "/cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("usuario", new UsuarioViewModel());
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar")
    public String cadastrar(UsuarioViewModel usuario, MultipartFile file) {
        
        //TODO: Refatorar, passar para o Serviço

        _servicoUsuario.criar(usuario, file);
        return "login";
    }
}
