
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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author diuly.barreto
 */

@Entity
@Table(name = "TOKEN", indexes = {
    @Index(columnList = "VL_TOKEN", name = "IX_TOKEN_VL"),
    @Index(columnList = "ID_USUARIO", name = "IX_TOKEN_US"),
    @Index(columnList = "ST_TOKEN", name = "IX_TOKEN_ST")
})
public class Token implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TOKEN")
    @SequenceGenerator(name = "SEQ_TOKEN", sequenceName = "SEQ_TOKEN", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_TOKEN")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "VL_TOKEN")
    private String valor;
    
    @Basic(optional = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_TOKEN_USUARIO_US"))
    private Usuario usuario;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = true)
    @Column(name = "ST_TOKEN")
    private StatusToken status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusToken getStatus() {
        return status;
    }

    public void setStatus(StatusToken status) {
        this.status = status;
    }
}
