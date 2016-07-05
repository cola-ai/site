
package br.com.cwi.colaai.web.controllers.rest;

import br.com.cwi.colaai.entity.view_model.BasicoUsuarioViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Érico de Souza Loewe
 */
@RestController
@RequestMapping(value = "/rest/usuario")
public class UsuarioRestController {
    
    @Autowired
    UsuarioServico servicoUsuario;
    
    @Autowired
    SocialUserDetailsService userDetailsService;
    
    @RequestMapping(value = "/existeUsuarioComEmail")
    public boolean existeUsuarioComEmail(String email) {
        return servicoUsuario.buscarAutorizadoPorEmail(email) != null;
    }
    
    @RequestMapping(value = "/naoExisteUsuarioComEmail")
    public boolean naoExisteUsuarioComEmail(String email) {
        return servicoUsuario.buscarAutorizadoPorEmail(email) == null;
    }
    
    @RequestMapping(value = "/getUsuarioAtual")
    public BasicoUsuarioViewModel getUsuarioAtual(String email) {
        return servicoUsuario.buscarPorId(getUsuarioAtual().getUsuarioId()).toBasicoViewModel();
    }
    
    private InformacoesUsuarioAtual getUsuarioAtual() {
        return userDetailsService.getInformacoesUsuarioAtual();
    }
}
