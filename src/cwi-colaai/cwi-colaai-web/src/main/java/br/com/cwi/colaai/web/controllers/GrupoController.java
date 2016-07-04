
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.view_model.GrupoUsuarioViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
import br.com.cwi.colaai.entity.view_model.ItinerarioViewModel;
import br.com.cwi.colaai.entity.view_model.ListarGrupoViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.service.servicos.GrupoServico;
import br.com.cwi.colaai.service.servicos.GrupoUsuarioServico;
import br.com.cwi.colaai.service.servicos.ItinerarioServico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Alycio
 */
@Controller
@RequestMapping(value = "/grupo")
public class GrupoController {
    
    @Autowired
    GrupoServico grupoServico;
    
    @Autowired 
    ItinerarioServico itinerarioServico;
    
    @Autowired
    GrupoUsuarioServico grupoUsuarioServico;
    
    @RequestMapping(value = {"/criarGrupo", "/"}, method = RequestMethod.GET)
    public String criarGrupo(Model model){
        model.addAttribute("grupoViewModel", new GrupoViewModel());
        List<ListarGrupoViewModel> listaDeGrupos = grupoServico.getGruposUsuarioLidera(getInformacoesUsuarioAtual().getUsuarioId());
        model.addAttribute("listaDeGrupos", listaDeGrupos);
        return "grupo/criarGrupo";
    }
    
    @RequestMapping(value = "/criarGrupo", method = RequestMethod.POST)
    public String criarGrupo(GrupoViewModel grupoViewModel){
        grupoViewModel.setIdDonoGrupo(getInformacoesUsuarioAtual().getUsuarioId());
        grupoServico.criarGrupo(grupoViewModel);
        return "redirect:criarGrupo";
    }
    
    @RequestMapping(value = "/adicionarUsuario", method = RequestMethod.POST)
    public String adicionarUsuario(GrupoUsuarioViewModel grupoUsuarioViewModel){
        if (grupoUsuarioViewModel.getIdUsuario() != null && grupoUsuarioViewModel.getIdUsuario() > 0) {
            grupoUsuarioServico.adicionarUmUsuarioAoGrupo(grupoUsuarioViewModel);
            return "redirect:grupo/criarUsuario?usuarioAdicionado";
        }
        return "redirect:grupo/criarGrupo";
    }
    
    @RequestMapping(value = "/adicionarItinerario", method = RequestMethod.POST)
    public String adicionarItinerarios(GrupoViewModel grupo, int id){
        List<Long> lista = grupo.getIdItinerarios();
        grupoServico.adicionarItinerariosAoGrupo(lista, new Long(id));
        return "redirect:/grupo/administrarGrupo?id="+id;
    }
    @RequestMapping(value = "/removerItinerario", method = RequestMethod.POST)
    public String removerItinerarios(GrupoViewModel grupo, int id){
        List<Long> lista = grupo.getIdItinerarios();
        grupoServico.removerItinerariosdoGrupo(lista);
        return "redirect:/grupo/administrarGrupo?id="+id;
    }
    
    @RequestMapping(value = "/removerGrupo", method = RequestMethod.POST)
    public String removerGrupo(int id){
        boolean removido = grupoServico.removerGrupo(new Long(id));
        
        if(removido){
            return "redirect:/grupo/criarGrupo?removido";
        }
        return "redirect:/grupo/criarGrupo?erro";

    }
    
    @RequestMapping(value="/administrarGrupo", method= RequestMethod.GET)
    public String administrarGrupo(int id, Model model){
        
        //TODO Internacionalizar a pagina de administrar Grupo e criar Grupo
        
        Long idUsuarioLogado = getInformacoesUsuarioAtual().getUsuarioId();
        List<ItinerarioViewModel> itinerariosDoUsuario = itinerarioServico.buscarItinerariosDoUsuario(idUsuarioLogado);
        List<ItinerarioViewModel> itinerariosDoGrupo = itinerarioServico.buscarItinerariosDoGrupo(new Long(id));
        model.addAttribute("id", id);
        model.addAttribute("itinerarioDoUsuario", itinerariosDoUsuario);
        model.addAttribute("itinerarioDoGrupo", itinerariosDoGrupo);
        return "grupo/administrarGrupo";
    } 
    
    @RequestMapping(value = {"/pesquisar", "/"}, method = RequestMethod.GET)
    public String pesquisar() {
        return "grupo/pesquisar";
    }
    
    private static InformacoesUsuarioAtual getInformacoesUsuarioAtual() {
        return (InformacoesUsuarioAtual) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
