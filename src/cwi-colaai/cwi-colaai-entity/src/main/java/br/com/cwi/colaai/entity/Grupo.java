
package br.com.cwi.colaai.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Alycio
 */
@Entity
@Table(name = "GRUPO")
public class Grupo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GRUPO")
    @SequenceGenerator(name = "SEQ_GRUPO", sequenceName = "SEQ_GRUPO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO")
    private Long id; 
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_GRUPO_USUARIO_LI"))
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Usuario lider;
    
    @Basic(optional = false)
    @Column(name = "NM_GRUPO")
    private String nomeGrupo;
    
    @Basic(optional = false)
    @Column(name ="QT_VAGAS_GRUPO")
    private Integer quantidadeDeVagas;
    
    @OneToMany(mappedBy="grupo")
    private List<Itinerario> itinerarios;
    
    @OneToMany(mappedBy="grupo")
    private List<GrupoUsuario> usuarios;
    
    @OneToMany(mappedBy="grupoSolicitado")
    private List<Solicitacao> solicitacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getLider() {
        return lider;
    }

    public void setLider(Usuario lider) {
        this.lider = lider;
    }

    public Integer getQuantidadeDeVagas() {
        return quantidadeDeVagas;
    }

    public void setQuantidadeDeVagas(Integer quantidadeDeVagas) {
        this.quantidadeDeVagas = quantidadeDeVagas;
    }

    public List<Itinerario> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<Itinerario> itinerarios) {
        this.itinerarios = itinerarios;
    }

    public List<GrupoUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<GrupoUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }
}
