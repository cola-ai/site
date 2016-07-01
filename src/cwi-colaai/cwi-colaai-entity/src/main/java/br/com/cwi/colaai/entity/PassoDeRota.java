
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
 * @author Érico de Souza Loewe
 */
@Entity
@Table(name = "PASSO_DE_ROTA")
public class PassoDeRota implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PASSO_DE_ROTA_SEQ")
    @SequenceGenerator(name = "ID_PASSO_DE_ROTA_SEQ", sequenceName = "ID_PASSO_DE_ROTA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_PASSO_DE_ROTA")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "DU_PASSO_DE_ROTA")
    private String duracao;
    
    @Basic(optional = false)
    @Column(name = "DI_PASSO_DE_ROTA")
    private String distancia;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PI_PASSO_DE_ROTA", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_PI"))
    private Geolocalizacao pontoInicio;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "LI_PASSO_DE_ROTA", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_LI"))
    private Geolocalizacao localizacaoInicio;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PF_PASSO_DE_ROTA", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_PF"))
    private Geolocalizacao pontoFim;
    
    @JoinColumn(name = "LF_PASSO_DE_ROTA", referencedColumnName = "ID_GEOLOCALIZACAO", foreignKey = @ForeignKey(name = "FK_PDR_GEO_LF"))
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)    
    private Geolocalizacao localizacaoFim;
    
    @OneToMany(mappedBy="passo", cascade = CascadeType.ALL)
    private List<Trajeto> trajetoria;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "RT_PASSO_DE_ROTA", referencedColumnName = "ID_ROTA", foreignKey = @ForeignKey(name = "FK_PASSO_DE_ROTA_ROTA"))
    private Rota rota;
}
