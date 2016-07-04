
package br.com.cwi.colaai.entity.view_model;

/**
 *
 * @author Érico de Souza Loewe
 */
public class BasicoGrupoViewModel {
    
    private Long idGrupo;
    private Integer quantidadeVagas;
    private String nome;
    private Long idDonoGrupo;

    public BasicoGrupoViewModel() {
    }

    public BasicoGrupoViewModel(Long idGrupo, Integer quantidadeVagas, String nome, Long idDonoGrupo) {
        this.idGrupo = idGrupo;
        this.quantidadeVagas = quantidadeVagas;
        this.nome = nome;
        this.idDonoGrupo = idDonoGrupo;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
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

    public Long getIdDonoGrupo() {
        return idDonoGrupo;
    }

    public void setIdDonoGrupo(Long idDonoGrupo) {
        this.idDonoGrupo = idDonoGrupo;
    }
}
