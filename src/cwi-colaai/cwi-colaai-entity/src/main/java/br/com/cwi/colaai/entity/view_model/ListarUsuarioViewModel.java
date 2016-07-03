
package br.com.cwi.colaai.entity.view_model;

/**
 *
 * @author Érico de Souza Loewe
 */
public class ListarUsuarioViewModel {
    
    private Long id;
    private String nome;
    private String foto;

    public ListarUsuarioViewModel() {
    }

    public ListarUsuarioViewModel(Long id, String nome, String foto) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
