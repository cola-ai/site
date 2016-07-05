
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Grupo;
import br.com.cwi.colaai.entity.Itinerario;
import br.com.cwi.colaai.entity.Solicitacao;
import br.com.cwi.colaai.entity.StatusSolicitacao;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.FiltroGrupoViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoParaListarViewModel;
import br.com.cwi.colaai.entity.view_model.SolicitacaoViewModel;
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
    
    public List<Grupo> gruposDoUsuario(Long usuarioId) {
        List<Grupo> grupos = new ArrayList<>();
        Usuario usuario = usuarioRepositorio.findOne(usuarioId);
        
        grupoRepositorio.findAllByLider(usuario).forEach((g) -> {
            grupos.add(g);
        });
        
        usuario.getGrupos().forEach((g) -> {
            Grupo grupo = g.getGrupo();
            if(!grupos.contains(grupo)) {
                grupos.add(grupo);
            }
        });
        
        return grupos;
    }

    public List<GrupoParaListarViewModel> getGruposDoUsuario(Long usuarioId) {
        List<GrupoParaListarViewModel> grupos = new ArrayList<>();
        
        gruposDoUsuario(usuarioId).forEach((g) -> {
            grupos.add(g.toListarViewModel());
        });
        
        return grupos;
    }
    
    public List<GrupoParaListarViewModel> getGruposUsuarioLidera(Long usuarioId) {
        List<GrupoParaListarViewModel> grupos = new ArrayList<>();
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

    public List<GrupoParaListarViewModel> getGruposPorFiltro(FiltroGrupoViewModel filtro, Long usuarioId) {

        List<GrupoParaListarViewModel> gruposViewModel = new ArrayList<>();
        List<Grupo> grupos = new ArrayList<>();
        if (filtro.getDestino() == null && filtro.getDestino() == null && filtro.getOrigem() == null) {
            grupos = grupoRepositorio.findByNomeContainingIgnoreCaseAndLider_IdNot(filtro.getNome() , usuarioId);
            grupos.forEach((grupo)-> {gruposViewModel.add(grupo.toListarViewModel());});
            return gruposViewModel;
        }
        List<Itinerario> itinerarios = new ArrayList<>();
        if (filtro.getNome() == null)
            filtro.setNome("");       
        if(filtro.getDestino() == null )
            filtro.setDestino("");        
        if(filtro.getOrigem() == null)
            filtro.setOrigem("");
        if(filtro.getHorario() == null)
            filtro.setHorario("");
        
        itinerarios = itinerarioRepositorio.findByOrigem_CidadeContainingIgnoreCaseAndDestino_CidadeContainingIgnoreCaseAndHorarioSaidaContaining(filtro.getOrigem(), filtro.getDestino(), filtro.getHorario());
        grupos = grupoRepositorio.findByItinerariosInAndLider_IdNotAndNomeContainingIgnoreCase(itinerarios, usuarioId, filtro.getNome());
        grupos.forEach((grupo)-> {gruposViewModel.add(grupo.toListarViewModel());});
        
        return gruposViewModel;
    }

    public void enviarSolicitacao(Long idGrupo, Long idUsuario) {
        Usuario usuario = usuarioRepositorio.findOne(idUsuario);
        Grupo grupo = grupoRepositorio.findOne(idGrupo);
        if(solicitacaoRepositorio.findOneByUsuarioAndGrupoSolicitadoAndStatus(usuario, grupo, StatusSolicitacao.PENDENTE) == null) {
            solicitacaoRepositorio.save(new Solicitacao(usuario, grupo, StatusSolicitacao.PENDENTE));
        }
    }

    public void removerSolicitacao(Long idGrupo, Long usuarioId) {
        solicitacaoRepositorio.delete(solicitacaoRepositorio.findOneByUsuario_IdAndGrupoSolicitado_IdAndStatus(usuarioId, idGrupo, StatusSolicitacao.PENDENTE));
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

    public List<GrupoParaListarViewModel> getGruposRecomendados(Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findOne(usuarioId);
        List<GrupoParaListarViewModel> grupos = new ArrayList<>();
        List<Itinerario> itinerariosRelacionados = itinerarioServico.getItinerariosRelacionados(usuario);

        if(itinerariosRelacionados != null) {
            for(Itinerario i : itinerariosRelacionados) {
                GrupoParaListarViewModel grupo = i.getGrupo().toListarViewModelComStatus(usuario);
                if(!grupos.contains(grupo)) {
                    grupos.add(grupo);
                }
            }
        }
        
        return grupos;
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

    public boolean aceitarSolicitacao(Long idSolicitacao) {
        Solicitacao solicitacao = solicitacaoRepositorio.findOne(idSolicitacao);
        if(solicitacao != null) {
            solicitacao.setStatus(StatusSolicitacao.APROVADA);
            solicitacaoRepositorio.save(solicitacao);
            grupoUsuarioServico.adicionarUmUsuarioAoGrupo(solicitacao.getGrupoSolicitado().getId(), solicitacao.getUsuario().getId());
            return true;
        }
        return false;
    }
    
    public boolean recusarSolicitacao(Long idSolicitacao) {
        Solicitacao solicitacao = solicitacaoRepositorio.findOne(idSolicitacao);
        if(solicitacao != null) {
            solicitacao.setStatus(StatusSolicitacao.REPROVADA);
            solicitacaoRepositorio.save(solicitacao);
            return true;
        }
        return false;
    }
}
