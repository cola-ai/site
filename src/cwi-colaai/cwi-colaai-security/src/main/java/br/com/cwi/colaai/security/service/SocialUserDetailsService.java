package br.com.cwi.colaai.security.service;

import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.enumeration.SocialRoles;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author erico.loewe
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Override
    public InformacoesUsuarioAtual loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioViewModel usuario = new UsuarioViewModel("cola-ai", username, new BCryptPasswordEncoder().encode("123"));
        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        return new InformacoesUsuarioAtual(username, usuario.getSenha(), SocialRoles.valuesToList(), usuario);
    }

}
