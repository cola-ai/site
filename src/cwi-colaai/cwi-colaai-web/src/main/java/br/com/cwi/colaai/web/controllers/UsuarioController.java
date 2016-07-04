package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.servicos.PessoaServico;
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
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alycio.neto
 */
@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private static final Logger LOGGER = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    UsuarioServico usuarioServico;
    
    @Autowired
    SocialUserDetailsService userDetailsService;
    
    @Autowired PessoaServico pessoaServico ;

    @RequestMapping(value = "/configuracoes")
    public String configuracoes(Model model) {
        UsuarioViewModel usuarioViewModel = usuarioServico.buscarPorEmail(userDetailsService.getInformacoesUsuarioAtual().getEmail());
        model.addAttribute("usuarioViewModel", usuarioViewModel);
        return "usuario/configuracoes";
    }

    @RequestMapping(value = "/alterarCadastro", method = RequestMethod.POST)
    public String alterarDadosCadastrais(UsuarioViewModel usuario) {
        pessoaServico.alterarDadosCadastro(usuario);
        return "redirect:configuracoes?salvo";
    }

    @RequestMapping(value = "/alterarImagem", method = RequestMethod.POST)
    public String alterarImagem(UsuarioViewModel usuario, MultipartFile file) {
        try {
            usuarioServico.alterarImagem(usuario, new ImagemViewModel(file.getName(), file.getOriginalFilename(), file.getInputStream()));
            return "redirect:configuracoes?salvo";
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return "redirect:configuracoes?erro";
    }

    @RequestMapping(value = "/alterarSenha", method = RequestMethod.POST)
    public String alterarSenha(UsuarioViewModel usuario) {

        //TODO Validar segurança Senha
        usuarioServico.alterarSenha(usuario);
        return "redirect:configuracoes?salvo";
    }

    @RequestMapping(value = "/cadastrar", method = GET)
    public String cadastrar(Model model, UsuarioViewModel usuarioViewModel) {
        model.addAttribute("usuarioModel", new UsuarioViewModel());
        return "usuario/cadastrar";
    }

    @RequestMapping(value = "/cadastrar", method = POST)
    public String cadastrar(@Valid UsuarioViewModel usuarioViewModel, BindingResult result, MultipartFile file, Model model) {
        if (result.hasErrors()) {
            return "cadastrar";
        }

        try {
            if (!usuarioServico.criar(usuarioViewModel, new ImagemViewModel(file.getName(), file.getOriginalFilename(), file.getInputStream()))) {
                return "redirect:/usuario/cadastrar?emailJaExiste";
            }

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return "redirect:/acess/login?cadastro";
    }

}
