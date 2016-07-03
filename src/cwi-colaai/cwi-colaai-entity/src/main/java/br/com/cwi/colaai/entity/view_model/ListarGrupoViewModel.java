
package br.com.cwi.colaai.entity.view_model;

import java.util.List;

/**
 *
 * @author Érico de Souza Loewe
 */
public class ListarGrupoViewModel {
    
    private Long id;
    private Integer quantidadeVagas;
    private String nome;
    private List<BasicoItinerarioViewModel> itinerarios;
    private ListarUsuarioViewModel lider;
    private List<ListarUsuarioViewModel> participantes;

    public ListarGrupoViewModel() {
    }

    public ListarGrupoViewModel(Long id, Integer quantidadeVagas, String nome, List<BasicoItinerarioViewModel> itinerarios, ListarUsuarioViewModel lider, List<ListarUsuarioViewModel> participantes) {
        this.id = id;
        this.quantidadeVagas = quantidadeVagas;
        this.nome = nome;
        this.itinerarios = itinerarios;
        this.lider = lider;
        this.participantes = participantes;
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

    public ListarUsuarioViewModel getLider() {
        return lider;
    }

    public void setLider(ListarUsuarioViewModel lider) {
        this.lider = lider;
    }

    public List<ListarUsuarioViewModel> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<ListarUsuarioViewModel> participantes) {
        this.participantes = participantes;
    }
}
