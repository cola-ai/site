
package br.com.cwi.colaai.entity;

import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author diuly.barreto
 */
@Entity
@Table(name = "USUARIO", uniqueConstraints = @UniqueConstraint(columnNames={"DS_EMAIL"}))
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "DS_EMAIL")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "DS_SENHA")
    private String senha;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = true)
    @Column(name = "TP_RS_USUARIO")
    private RedeSocial redeSocial;
    
    @Basic(optional = true)
    @Column(name = "DS_IMAGEM")
    private String imagem;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_USUARIO_PESSOA_PE"))
    private Pessoa pessoa;
    
    @Basic(optional = false)
    @Column(name = "DS_AUTORIZADO")
    private Boolean estaAutorizado;
    
    @OneToMany(mappedBy="usuario")
    private List<Itinerario> itinerarios;
    
    @OneToMany(mappedBy="usuario")
    private List<GrupoUsuario> grupos;
    
    @OneToMany(mappedBy="lider")
    private List<Grupo> gruposSobLideranca;
    
    @OneToMany(mappedBy="usuario")
    private List<Solicitacao> solicitacoes;

    public List<GrupoUsuario> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoUsuario> grupos) {
        this.grupos = grupos;
    }

    public Boolean getEstaAutorizado() {
        return estaAutorizado;
    }

    public void setEstaAutorizado(Boolean estaAutorizado) {
        this.estaAutorizado = estaAutorizado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RedeSocial getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(RedeSocial redeSocial) {
        this.redeSocial = redeSocial;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Itinerario> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<Itinerario> itinerarios) {
        this.itinerarios = itinerarios;
    }

    public List<Grupo> getGruposSobLideranca() {
        return gruposSobLideranca;
    }

    public void setGruposSobLideranca(List<Grupo> gruposSobLideranca) {
        this.gruposSobLideranca = gruposSobLideranca;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public UsuarioViewModel toUsuarioViewModel() {
        return new UsuarioViewModel(id, pessoa.getNome(),pessoa.getSobrenome(), pessoa.getTelefone(), email, senha, imagem, pessoa.getSexo());
    }
}
