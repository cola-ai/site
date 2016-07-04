
package br.com.cwi.colaai.entity;

import br.com.cwi.colaai.entity.view_model.BasicoGrupoViewModel;
import br.com.cwi.colaai.entity.view_model.BasicoItinerarioViewModel;
import br.com.cwi.colaai.entity.view_model.GrupoViewModel;
import br.com.cwi.colaai.entity.view_model.ListarGrupoViewModel;
import br.com.cwi.colaai.entity.view_model.ListarUsuarioViewModel;
import java.io.Serializable;
import java.util.ArrayList;
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
    private String nome;
    
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GrupoViewModel toViewModel() {
        return new GrupoViewModel(id, quantidadeDeVagas, nome, lider.getId());
    }

    public ListarGrupoViewModel toListarViewModel() {
        ArrayList<ListarUsuarioViewModel> participantes = new ArrayList<>();
        ArrayList<BasicoItinerarioViewModel> itinerarios = new ArrayList<>();
        
        this.usuarios.forEach((u) -> {
            participantes.add(u.getUsuario().toListarViewModel());
        });
        this.itinerarios.forEach((i) -> {
            itinerarios.add(i.toBasicoViewModel());
        });
        
        return new ListarGrupoViewModel(id, quantidadeDeVagas, nome, itinerarios, lider.toListarViewModel(), participantes);
    }
    
    public ListarGrupoViewModel toListarViewModelComStatus(Usuario usuario) {
        ListarGrupoViewModel grupo = this.toListarViewModel();
        StatusSolicitacao ultimoStatus = null;
        
        for(Solicitacao s : solicitacoes) {
            if(s.getGrupoSolicitado().equals(this)) {
                ultimoStatus = s.getStatus();
            }
        }
        
        grupo.setStatus(ultimoStatus);
        return grupo;
    }
    
    public BasicoGrupoViewModel toBasicoViewModel() {
        return new BasicoGrupoViewModel(id, quantidadeDeVagas, nome, lider.getId());
    }
}
