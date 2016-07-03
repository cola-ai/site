/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
import br.com.cwi.colaai.service.repositorios.GrupoRepositorio;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alycio
 */
@Service
public class GrupoServico {
    @Autowired
    GrupoRepositorio grupoRepositorio;
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    public void criarGrupo(GrupoViewModel grupoViewModel){
        Usuario lider = usuarioRepositorio.findById(grupoViewModel.getIdDonoGrupo());
        Grupo grupo = new Grupo();
        grupo.setLiderGrupo(lider);
        grupo.setNomeGrupo(grupoViewModel.getNomeGrupo());
        grupo.setQuantidadeVagas(grupo.getQuantidadeVagas());
        grupoRepositorio.save(grupo);
    }
}
