
package br.com.cwi.colaai.entity.view_model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Alycio
 */
public class GrupoViewModel {
    private Long idGrupo;
    
    @Size(min=1) 
    private int quantidadeVagas;
    
    @NotNull
    @NotBlank
    @Size(min=6, max=255) 
    private String nome;
    
    @NotNull
    @NotBlank
    private Long idDonoGrupo;

    public GrupoViewModel() {
    }
    
    public GrupoViewModel(Long idGrupo, int quantidadeVagas, String nome, Long idDonoGrupo) {
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

    public int getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(int quantidadeVagas) {
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
