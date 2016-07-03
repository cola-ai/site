
package br.com.cwi.colaai.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Érico de Souza Loewe
 */
@Entity
@Table(name = "SOLICITACAO")
public class Solicitacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_SOLICITACAO")
    @SequenceGenerator(name = "SEQ_SOLICITACAO", sequenceName = "SEQ_SOLICITACAO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_SOLICITACAO")
    private Long id;
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_SOLICITACAO_USUARIO_US"))
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Usuario usuario;
    
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO", foreignKey = @ForeignKey(name = "FK_SOLICITACAO_GRUPO_GS"))
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Grupo grupoSolicitado;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "TP_STATUS_SOLICITACAO")
    private StatusSolicitacao status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupoSolicitado() {
        return grupoSolicitado;
    }

    public void setGrupoSolicitado(Grupo grupoSolicitado) {
        this.grupoSolicitado = grupoSolicitado;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }
}
