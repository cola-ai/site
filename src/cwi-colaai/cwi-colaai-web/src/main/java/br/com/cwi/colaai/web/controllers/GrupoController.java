/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.web.controllers;

import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.view_model.GrupoUsuarioViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
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
        Long idUsuarioLogado = getInformacoesUsuarioAtual().getUsuarioId();
        List<Itinerario> itinerariosDoUsuario = itinerarioServico.buscarItinerariosDoUsuario(idUsuarioLogado);
        model.addAttribute("itinerarioViewModel", itinerariosDoUsuario);
        return "grupo/criarGrupo";
    }
    
    @RequestMapping(value = "/criarGrupo", method = RequestMethod.POST)
    public String criarGrupo(GrupoViewModel grupoViewModel){
        grupoViewModel.setIdDonoGrupo(getInformacoesUsuarioAtual().getUsuarioId());
        grupoServico.criarGrupo(grupoViewModel);
        return "redirect:criarGrupo";
    }
    
    @RequestMapping(value = "/adicionarUsuario", method = RequestMethod.POST)
    public String criarGrupo(GrupoUsuarioViewModel grupoUsuarioViewModel){
        if (grupoUsuarioViewModel.getIdUsuario() != null && grupoUsuarioViewModel.getIdUsuario() > 0) {
            grupoUsuarioServico.adicionarUmUsuarioAoGrupo(grupoUsuarioViewModel);
            return "redirect:grupo/criarUsuario?usuarioAdicionado";
        }
        return "redirect:grupo/criarUsuario?erro";
    }
    
     @RequestMapping(value = "/adicionarItinerario", method = RequestMethod.POST)
    public String criarGrupo(List<Itinerario> itinerario){
       //TODO ADICIONAR ITINERARIOS
        return "redirect:grupo/criarUsuario?erro";
    }
    
    private static InformacoesUsuarioAtual getInformacoesUsuarioAtual() {
        return (InformacoesUsuarioAtual) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
