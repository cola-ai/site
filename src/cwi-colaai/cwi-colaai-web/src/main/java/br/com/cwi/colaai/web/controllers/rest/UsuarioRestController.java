
package br.com.cwi.colaai.web.controllers.rest;

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
    UsuarioServico _servicoUsuario;
    
    @RequestMapping(value = "/existeUsuarioComEmail")
    public boolean existeUsuarioComEmail(String email) {
        return _servicoUsuario.buscarAutorizadoPorEmail(email) != null;
    }
    
    @RequestMapping(value = "/naoExisteUsuarioComEmail")
    public boolean naoExisteUsuarioComEmail(String email) {
        return _servicoUsuario.buscarAutorizadoPorEmail(email) == null;
    }
}
