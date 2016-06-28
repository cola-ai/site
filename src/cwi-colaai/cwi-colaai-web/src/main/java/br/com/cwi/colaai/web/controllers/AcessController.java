package br.com.cwi.colaai.web.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import br.com.cwi.colaai.service.servicos.ServicoUsuario;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Érico de Souza Loewe
 */
@Controller
public class AcessController {

    public static String ROOT = "C:\\Users\\alycio.neto\\Desktop\\TesteProjeto\\TesteProjeto\\testes\\UploadImagem\\src\\main\\Resources\\static\\img\\";

    private final ResourceLoader resourceLoader;

    @Autowired
    public AcessController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private ServicoUsuario _servicoUsuario = new ServicoUsuario();

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
    public String cadastrar(Model model) throws IOException {
        model.addAttribute("usuario", new UsuarioViewModel());
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar")
    public String cadastrar(UsuarioViewModel usuario, MultipartFile file,
            RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try {
                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (IOException | RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }
        usuario.setImagem(file.getName());
        _servicoUsuario.criar(usuario);
        return "login";
    }
}
