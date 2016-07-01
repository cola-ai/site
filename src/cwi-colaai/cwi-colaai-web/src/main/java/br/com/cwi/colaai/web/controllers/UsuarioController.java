/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.service.servicos.PessoaServico;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.IOException;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alycio.neto
 */
@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
    
    @Autowired UsuarioServico usuarioServico;
    @Autowired PessoaServico pessoaServico;
    
    @RequestMapping(value = "configuracoes")
    public String configuracoes(Model model){   
        UsuarioViewModel usuarioViewModel = usuarioServico.buscarPorEmail(getInformacoesUsuarioAtual().getEmail());
        model.addAttribute("usuarioViewModel", usuarioViewModel);
        return "usuario/configuracoes";
    }
    
    
    @RequestMapping(value = "alterarCadastro", method = RequestMethod.POST)
    public String alterarDadosCadastrais(UsuarioViewModel usuario){
        pessoaServico.alterarDadosCadastro(usuario);
        return "redirect:configuracoes?salvo";
    }
    
    @RequestMapping(value = "alterarImagem", method = RequestMethod.POST)
    public String alterarImagem(UsuarioViewModel usuario, MultipartFile file){
        try {
            usuarioServico.alterarImagem(usuario, new ImagemViewModel(file.getName(), file.getOriginalFilename(), file.getInputStream()));
                return "redirect:configuracoes?salvo";      
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        return "redirect:configuracoes?erro";
    }
    
    @RequestMapping(value = "alterarSenha", method = RequestMethod.POST)
    public String alterarSenha(UsuarioViewModel usuario){
        
        //TODO Validar segurança Senha
        usuarioServico.alterarSenha(usuario);
        return "redirect:configuracoes?salvo";
    }  
    private static InformacoesUsuarioAtual getInformacoesUsuarioAtual() {
        return (InformacoesUsuarioAtual) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
