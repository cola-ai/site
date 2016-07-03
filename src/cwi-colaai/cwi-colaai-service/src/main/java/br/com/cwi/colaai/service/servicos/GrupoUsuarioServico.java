/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.GrupoUsuario;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.GrupoUsuarioViewModel;
import br.com.cwi.colaai.service.repositorios.GrupoRepositorio;
import br.com.cwi.colaai.service.repositorios.GrupoUsuarioRepositorio;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alycio
 */
@Service
public class GrupoUsuarioServico {
    @Autowired
    GrupoUsuarioRepositorio grupoUsuarioRepositorio;
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    GrupoRepositorio grupoRepositorio;
    
    public boolean adicionarUmUsuarioAoGrupo(GrupoUsuarioViewModel grupoUsuarioViewModel){
        Grupo grupo = grupoRepositorio.findOne(grupoUsuarioViewModel.getIdGrupo());
        
        if (grupo.getQuantidadeVagas() > 0) {
            Usuario usuario = usuarioRepositorio.findById(grupoUsuarioViewModel.getIdUsuario());
            GrupoUsuario grupoUsuario = new GrupoUsuario();
            grupoUsuario.setGrupo(grupo);
            grupoUsuario.setUsuario(usuario);
            grupoUsuarioRepositorio.save(grupoUsuario);
            grupo.setQuantidadeVagas(grupo.getQuantidadeVagas() - 1);
            return true;
        }
        return false;
    }
}
