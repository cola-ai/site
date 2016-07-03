/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private String nomeGrupo;
    
    @NotNull
    @NotBlank
    private Long idDonoGrupo;

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

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Long getIdDonoGrupo() {
        return idDonoGrupo;
    }

    public void setIdDonoGrupo(Long idDonoGrupo) {
        this.idDonoGrupo = idDonoGrupo;
    }

    public GrupoViewModel(Long idGrupo, int quantidadeVagas, String nomeGrupo, Long idDonoGrupo) {
        this.idGrupo = idGrupo;
        this.quantidadeVagas = quantidadeVagas;
        this.nomeGrupo = nomeGrupo;
        this.idDonoGrupo = idDonoGrupo;
    }

    public GrupoViewModel() {
    }
    
    
}
