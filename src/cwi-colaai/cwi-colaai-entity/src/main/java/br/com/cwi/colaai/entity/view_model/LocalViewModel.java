
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.Local;

/**
 *
 * @author Érico de Souza Loewe
 */
public class LocalViewModel {
    
    private GeolocalizacaoVieModel localizacao;
    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;

    public LocalViewModel() {
    }

    public LocalViewModel(GeolocalizacaoVieModel localizacao, String pais, String estado, String cidade, String cep, String bairro, String rua, String numero) {
        this.localizacao = localizacao;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public GeolocalizacaoVieModel getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(GeolocalizacaoVieModel localizacao) {
        this.localizacao = localizacao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public Local toLocal() {
        return new Local(localizacao.toGeolocalizacao(), pais, estado, cidade, cep, bairro, rua, numero);
    }
}
