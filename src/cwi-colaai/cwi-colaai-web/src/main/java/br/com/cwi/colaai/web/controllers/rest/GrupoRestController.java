
package br.com.cwi.colaai.web.controllers.rest;

import br.com.cwi.colaai.entity.view_model.ListarGrupoViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.service.servicos.GrupoServico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Érico de Souza Loewe
 */
@RestController
@RequestMapping(value = "/rest/grupo")
public class GrupoRestController {
    
    @Autowired
    private GrupoServico grupoServico;
    
    @RequestMapping(value = "/gruposDoUsuarioAtual", method = RequestMethod.GET)
    List<ListarGrupoViewModel> gruposDoUsuarioAtual() {
        return grupoServico.getGruposDoUsuario(getUsuarioAtual().getUsuarioId());
    }
    
    private static InformacoesUsuarioAtual getUsuarioAtual() {
        return (InformacoesUsuarioAtual) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
