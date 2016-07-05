
package br.com.cwi.colaai.entity.view_model;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Alycio
 */
public class GrupoViewModel {
    private Long idGrupo;
    
    @Min(0)
    @Max(6)
    private Integer quantidadeVagas;
    
    @NotNull
    @NotBlank
    @Size(min=3, max=255) 
    private String nome;
    
    @NotNull
    @NotBlank
    private Long idDonoGrupo;
    
    private List<Long> idItinerarios ;

    public List<Long> getIdItinerarios() {
        return idItinerarios;
    }

    public void setIdItinerarios(List<Long> idItinerarios) {
        this.idItinerarios = idItinerarios;
    }

    public GrupoViewModel() {
    }
    
    public GrupoViewModel(Long idGrupo, Integer quantidadeVagas, String nome, Long idDonoGrupo) {
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
