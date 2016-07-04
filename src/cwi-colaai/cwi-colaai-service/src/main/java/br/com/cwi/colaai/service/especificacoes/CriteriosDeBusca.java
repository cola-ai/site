
package br.com.cwi.colaai.service.especificacoes;

/**
 *
 * @author Érico de Souza Loewe
 */
public class CriteriosDeBusca {
    private String chave;
    private String operacao;
    private Object valor;

    public CriteriosDeBusca() {
    }

    public CriteriosDeBusca(String chave, String operacao, Object valor) {
        this.chave = chave;
        this.operacao = operacao;
        this.valor = valor;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
