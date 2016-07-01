/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diuly.barreto
 */

@Controller
public class CadastroController {
    
    private static final Logger LOGGER = Logger.getLogger(AcessController.class.getName());
    
    @Autowired
    UsuarioServico _servicoUsuario;
    
    @RequestMapping(value = "/cadastrar", method = GET)
    public String cadastrar(Model model, UsuarioViewModel usuarioViewModel) {
        model.addAttribute("usuarioModel", new UsuarioViewModel());
        return "cadastrar";
    }

    @RequestMapping(value = "/cadastrar", method = POST)
    public String cadastrar(@Valid UsuarioViewModel usuarioViewModel, BindingResult result, MultipartFile file, Model model) {
        if (result.hasErrors()) {
            return "cadastrar";
        }

        try {
            if(!_servicoUsuario.criar(usuarioViewModel, new ImagemViewModel(file.getName(), file.getOriginalFilename(), file.getInputStream()))) 
                return "redirect:/cadastrar?emailJaExiste";
                
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return "redirect:/login?cadastro";
    }
}
