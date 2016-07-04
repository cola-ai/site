
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.Solicitacao;
import br.com.cwi.colaai.entity.StatusSolicitacao;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.FiltroGrupoViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
import br.com.cwi.colaai.entity.view_model.ListarGrupoViewModel;
import br.com.cwi.colaai.entity.view_model.SolicitacaoViewModel;
import br.com.cwi.colaai.service.especificacoes.ContrutorDeEspecificacaoDeGrupo;
import br.com.cwi.colaai.service.repositorios.GrupoRepositorio;
import br.com.cwi.colaai.service.repositorios.ItinerarioRepositorio;
import br.com.cwi.colaai.service.repositorios.SolicitacaoRepositorio;
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
    private GrupoUsuarioServico grupoUsuarioServico;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private ItinerarioRepositorio itinerarioRepositorio;
    
    @Autowired
    private ItinerarioServico itinerarioServico;
    
    @Autowired            
    private SolicitacaoRepositorio solicitacaoRepositorio;
    
    public void criarGrupo(GrupoViewModel grupoViewModel){
        Usuario lider = usuarioRepositorio.findById(grupoViewModel.getIdDonoGrupo());
        Grupo grupo = new Grupo();
        grupo.setLider(lider);
        grupo.setNome(grupoViewModel.getNome());
        grupo.setQuantidadeDeVagas(grupoViewModel.getQuantidadeVagas());
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
    
    public List<ListarGrupoViewModel> getGruposUsuarioLidera(Long usuarioId) {
        List<ListarGrupoViewModel> grupos = new ArrayList<>();
        Usuario usuario = usuarioRepositorio.findOne(usuarioId);
        usuario.getGruposSobLideranca().forEach((g) -> {
            grupos.add(g.toListarViewModel());
        });
        return grupos;
    }

        
    public void adicionarItinerariosAoGrupo(List<Long> listaDeIds, Long id){
        Grupo grupo = grupoRepositorio.findOne(id);
        List<Itinerario> itinerarios = itinerarioRepositorio.findByIdIn(listaDeIds);
        itinerarios.forEach((i) -> { i.setGrupo(grupo);});
        itinerarioRepositorio.save(itinerarios);
    }
    
    public void removerItinerariosdoGrupo(List<Long> listaDeIds){
        List<Itinerario> itinerarios = itinerarioRepositorio.findByIdIn(listaDeIds);
        itinerarios.forEach((i) -> { i.setGrupo(null);});
        itinerarioRepositorio.save(itinerarios);
    }

    public List<ListarGrupoViewModel> getGruposPorFiltro(FiltroGrupoViewModel filtro, Long usuarioId) {
        List<ListarGrupoViewModel> grupos = new ArrayList<>();
        ContrutorDeEspecificacaoDeGrupo construtor = new ContrutorDeEspecificacaoDeGrupo();
        Usuario usuario = usuarioRepositorio.findOne(usuarioId);
        
        construtor.with("id", "<>", usuarioId);
        
        if(!filtro.getNome().isEmpty()) {
            construtor.with("nome", ":", filtro.getNome());
        }
        
        grupoRepositorio.findAll(construtor.build()).forEach((g) -> {
            grupos.add(g.toListarViewModelComStatus(usuario));
        });
        
        return grupos;
    }

    public void enviarSolicitacao(Long idGrupo, Long idUsuario) {
        Usuario usuario = usuarioRepositorio.findOne(idUsuario);
        Grupo grupo = grupoRepositorio.findOne(idGrupo);
        if(solicitacaoRepositorio.findOneByUsuarioAndGrupoSolicitadoAndStatus(usuario, grupo, StatusSolicitacao.PENDENTE) == null) {
            solicitacaoRepositorio.save(new Solicitacao(usuario, grupo, StatusSolicitacao.PENDENTE));
        }
    }

    public void removerSolicitacao(Long idGrupo, Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findOne(usuarioId);
        Grupo grupo = grupoRepositorio.findOne(idGrupo);
        solicitacaoRepositorio.delete(solicitacaoRepositorio.findOneByUsuarioAndGrupoSolicitadoAndStatus(usuario, grupo, StatusSolicitacao.PENDENTE));
    }

    public void removerUsuarioDoGrupo(Long idGrupo, Long usuarioId) {
        grupoUsuarioServico.removerUsuarioDoGrupo(usuarioId, idGrupo);
    }

    public List<SolicitacaoViewModel> buscarMinhasSolicitacoes(Long usuarioId) {
        List<SolicitacaoViewModel> solicitacoes = new ArrayList<>();
        
        solicitacaoRepositorio.findAllByStatusSolicitacaoAndLider(StatusSolicitacao.PENDENTE, usuarioRepositorio.findOne(usuarioId)).forEach((solicitacao) -> {
            solicitacoes.add(solicitacao.toViewModel());
        });
        
        return solicitacoes;   
    }

    public List<ListarGrupoViewModel> getGruposRecomendados(Long usuarioId) {
        List<ListarGrupoViewModel> gruposViewModel = new ArrayList<>();
        List<Itinerario> itinerariosRelacionados = itinerarioServico.getItinerariosRelacionados(usuarioId);

        if(itinerariosRelacionados != null) {
            for(Itinerario i : itinerariosRelacionados) {
                gruposViewModel.add(i.getGrupo().toListarViewModel());
            }
        }
        
        return gruposViewModel;
    }
    
    public boolean removerGrupo(Long idGrupo){
        Grupo grupo = grupoRepositorio.findOne(idGrupo);
        if (grupo.getUsuarios().isEmpty() && grupo.getItinerarios().isEmpty()){ 
            grupoRepositorio.delete(grupo);
            return true;
        }
        else 
            return false;
    }
}
