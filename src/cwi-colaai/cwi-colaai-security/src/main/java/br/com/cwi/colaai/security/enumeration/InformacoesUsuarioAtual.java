package br.com.cwi.colaai.security.enumeration;

import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Érico de Souza Loewe
 */
public class InformacoesUsuarioAtual extends User {

    private final Long usuarioId;
    private final String nome;
    private final String email;
    private final String foto;
    
    public InformacoesUsuarioAtual(String username, String password, Collection<? extends GrantedAuthority> authorities, UsuarioViewModel usuario) {
        super(username, password, authorities);
        
        usuarioId = 0l;
        nome = usuario.getNome();
        email = usuario.getEmail();        
        foto = usuario.getFoto();
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getFoto() {
        return foto;
    }
}
