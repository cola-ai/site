
package br.com.cwi.colaai.entity.view_model;

import br.com.cwi.colaai.entity.SexoPessoa;

/**
 *
 * @author Érico de Souza Loewe
 */
public class BasicoUsuarioViewModel {
    private Long idUsuario;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String foto;
    private SexoPessoa sexo;

    public BasicoUsuarioViewModel() {
    }

    public BasicoUsuarioViewModel(Long idUsuario, String nome, String sobrenome, String telefone, String email, String foto, SexoPessoa sexo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.foto = foto;
        this.sexo = sexo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public SexoPessoa getSexo() {
        return sexo;
    }

    public void setSexo(SexoPessoa sexo) {
        this.sexo = sexo;
    }
}
