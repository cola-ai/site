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

    public InformacoesUsuarioAtual(String username, String password, Collection<? extends GrantedAuthority> authorities, UsuarioViewModel usuario) {
        super(username, password, authorities);    
    }
}
