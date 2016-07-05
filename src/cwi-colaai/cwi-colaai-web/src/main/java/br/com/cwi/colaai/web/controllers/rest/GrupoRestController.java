
package br.com.cwi.colaai.web.controllers.rest;

import br.com.cwi.colaai.entity.view_model.FiltroGrupoViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoParaListarViewModel;
import br.com.cwi.colaai.entity.view_model.SolicitacaoViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.service.SocialUserDetailsService;
import br.com.cwi.colaai.service.servicos.GrupoServico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    SocialUserDetailsService userDetailsService;
    
    @RequestMapping(value = "/gruposDoUsuarioAtual", method = RequestMethod.GET)
    List<GrupoParaListarViewModel> gruposDoUsuarioAtual() {
        return grupoServico.getGruposDoUsuario(getUsuarioAtual().getUsuarioId());
    }
    
    @RequestMapping(value = "/gruposRecomendados", method = RequestMethod.GET)
    List<GrupoParaListarViewModel> gruposRecomendados() {
        return grupoServico.getGruposRecomendados(getUsuarioAtual().getUsuarioId());
    }
    
    @RequestMapping(value = "/pesquisarComFiltro", method = RequestMethod.GET)
    List<GrupoParaListarViewModel> pesquisarComFiltro(FiltroGrupoViewModel filtro) {
        return grupoServico.getGruposPorFiltro(filtro, getUsuarioAtual().getUsuarioId());
    }
    
    @RequestMapping(value = "/enviarSolicitacao", method = RequestMethod.POST)
    String enviarSolicitacao(Long idGrupo) {
        grupoServico.enviarSolicitacao(idGrupo, getUsuarioAtual().getUsuarioId());
        return "Grupo solicitado com sucesso";
    }
    
    @RequestMapping(value = "/aceitarSolicitacao", method = RequestMethod.POST)
    String aceitarSolicitacao(Long idSolicitacao) {
        if(grupoServico.aceitarSolicitacao(idSolicitacao)) {;
            return "Solicitacao aceita com sucesso";
        }
        return "Problemas ao aceitar solicitacao";
    }
    
    @RequestMapping(value = "/recusarSolicitacao", method = RequestMethod.POST)
    String recusarSolicitacao(Long idSolicitacao) {
        if(grupoServico.recusarSolicitacao(idSolicitacao)) {
            return "Solicitacao recusada com sucesso";
        }
        return "Problemas ao recusar solicitacao";
    }
    
    @RequestMapping(value = "/removerSolicitacao", method = RequestMethod.POST)
    String removerSolicitacao(Long idGrupo) {
        grupoServico.removerSolicitacao(idGrupo, getUsuarioAtual().getUsuarioId());
        return "Solicitação removida com sucesso";
    }
    
    @RequestMapping(value = "/removerUsuarioDoGrupo", method = RequestMethod.POST)
    String removerUsuarioDoGrupo(Long idGrupo) {
        grupoServico.removerUsuarioDoGrupo(idGrupo, getUsuarioAtual().getUsuarioId());
        return "Usuario removido com sucesso";
    }
    
    @RequestMapping(value = "/minhasSolicitacoes", method = RequestMethod.GET)
    List<SolicitacaoViewModel> minhasSolicitacoes() {
        return grupoServico.buscarMinhasSolicitacoes(getUsuarioAtual().getUsuarioId());
    }
    
    private InformacoesUsuarioAtual getUsuarioAtual() {
        return userDetailsService.getInformacoesUsuarioAtual();
    }
}
