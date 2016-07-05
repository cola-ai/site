
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.StatusSolicitacao;
import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class GrupoParaListarViewModel {
    
    private Long id;
    private Integer quantidadeVagas;
    private String nome;
    private List<BasicoItinerarioViewModel> itinerarios;
    private UsuarioParaListarViewModel lider;
    private List<UsuarioParaListarViewModel> participantes;
    private StatusSolicitacao status;

    public GrupoParaListarViewModel() {
    }

    public GrupoParaListarViewModel(Long id, Integer quantidadeVagas, String nome, List<BasicoItinerarioViewModel> itinerarios, UsuarioParaListarViewModel lider, List<UsuarioParaListarViewModel> participantes) {
        this.id = id;
        this.quantidadeVagas = quantidadeVagas;
        this.nome = nome;
        this.itinerarios = itinerarios;
        this.lider = lider;
        this.participantes = participantes;
    }

    public GrupoParaListarViewModel(Long id, Integer quantidadeVagas, String nome, List<BasicoItinerarioViewModel> itinerarios, UsuarioParaListarViewModel lider, List<UsuarioParaListarViewModel> participantes, StatusSolicitacao status) {
        this.id = id;
        this.quantidadeVagas = quantidadeVagas;
        this.nome = nome;
        this.itinerarios = itinerarios;
        this.lider = lider;
        this.participantes = participantes;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(Integer quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<BasicoItinerarioViewModel> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<BasicoItinerarioViewModel> itinerarios) {
        this.itinerarios = itinerarios;
    }

    public UsuarioParaListarViewModel getLider() {
        return lider;
    }

    public void setLider(UsuarioParaListarViewModel lider) {
        this.lider = lider;
    }

    public List<UsuarioParaListarViewModel> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<UsuarioParaListarViewModel> participantes) {
        this.participantes = participantes;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }
}
