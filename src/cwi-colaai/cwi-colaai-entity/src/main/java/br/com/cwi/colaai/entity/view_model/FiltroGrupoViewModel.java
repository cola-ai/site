
package br.com.cwi.colaai.entity.view_model;

/**
 *
 * @author Érico de Souza Loewe
 */
public class FiltroGrupoViewModel {
    
    private String origem;
    private String destino;
    private String nome;
    private String horario;

    public FiltroGrupoViewModel() {
    }

    public FiltroGrupoViewModel(String origem, String destino, String nome, String horario) {
        this.origem = origem;
        this.destino = destino;
        this.nome = nome;
        this.horario = horario;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
