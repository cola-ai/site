
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
    private GrupoUsuarioRepositorio grupoUsuarioRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private GrupoRepositorio grupoRepositorio;
    
    public boolean adicionarUmUsuarioAoGrupo(GrupoUsuarioViewModel grupoUsuarioViewModel){
        Grupo grupo = grupoRepositorio.findOne(grupoUsuarioViewModel.getIdGrupo());
        
        if (grupo.getQuantidadeDeVagas() > 0) {
            Usuario usuario = usuarioRepositorio.findById(grupoUsuarioViewModel.getIdUsuario());
            GrupoUsuario grupoUsuario = new GrupoUsuario();
            grupoUsuario.setGrupo(grupo);
            grupoUsuario.setUsuario(usuario);
            grupoUsuarioRepositorio.save(grupoUsuario);
            grupo.setQuantidadeDeVagas(grupo.getQuantidadeDeVagas() - 1);
            return true;
        }
        return false;
    }
    
    public void removerUsuarioDoGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = usuarioRepositorio.findOne(usuarioId);
        Grupo grupo = grupoRepositorio.findOne(grupoId);
        
        grupoUsuarioRepositorio.delete(grupoUsuarioRepositorio.findOneByGrupoAndUsuario(grupo, usuario));
    }
}
