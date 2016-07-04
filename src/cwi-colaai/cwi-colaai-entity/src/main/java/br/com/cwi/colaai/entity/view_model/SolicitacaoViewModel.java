
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.StatusSolicitacao;

/**
 *
 * @author Érico de Souza Loewe
 */
public class SolicitacaoViewModel {
    
    private Long idSolicitacao;
    private BasicoUsuarioViewModel usuario;
    private BasicoGrupoViewModel grupo;
    private StatusSolicitacao status;

    public SolicitacaoViewModel() {
    }

    public SolicitacaoViewModel(Long idSolicitacao, BasicoUsuarioViewModel usuario, BasicoGrupoViewModel grupo, StatusSolicitacao status) {
        this.idSolicitacao = idSolicitacao;
        this.usuario = usuario;
        this.grupo = grupo;
        this.status = status;
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public BasicoUsuarioViewModel getUsuario() {
        return usuario;
    }

    public void setUsuario(BasicoUsuarioViewModel usuario) {
        this.usuario = usuario;
    }

    public BasicoGrupoViewModel getGrupo() {
        return grupo;
    }

    public void setGrupo(BasicoGrupoViewModel grupo) {
        this.grupo = grupo;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }
}
