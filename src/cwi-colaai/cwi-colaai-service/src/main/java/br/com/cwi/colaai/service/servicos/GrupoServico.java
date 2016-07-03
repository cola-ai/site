
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
import br.com.cwi.colaai.entity.view_model.ListarGrupoViewModel;
import br.com.cwi.colaai.service.repositorios.GrupoRepositorio;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alycio
 */
@Service
public class GrupoServico {
    @Autowired
    private GrupoRepositorio grupoRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public void criarGrupo(GrupoViewModel grupoViewModel){
        Usuario lider = usuarioRepositorio.findById(grupoViewModel.getIdDonoGrupo());
        Grupo grupo = new Grupo();
        grupo.setLider(lider);
        grupo.setNome(grupoViewModel.getNome());
        grupo.setQuantidadeDeVagas(grupo.getQuantidadeDeVagas());
        grupoRepositorio.save(grupo);
    }

    public List<ListarGrupoViewModel> getGruposDoUsuario(Long usuarioId) {
        List<ListarGrupoViewModel> grupos = new ArrayList<>();
        Usuario usuario = usuarioRepositorio.findOne(usuarioId);
        usuario.getGrupos().forEach((g) -> {
            grupos.add(g.getGrupo().toListarViewModel());
        });
        return grupos;
    }
}
