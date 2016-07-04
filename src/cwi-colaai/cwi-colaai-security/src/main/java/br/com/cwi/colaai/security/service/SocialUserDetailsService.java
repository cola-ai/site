package br.com.cwi.colaai.security.service;

import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.security.enumeration.InformacoesUsuarioAtual;
import br.com.cwi.colaai.security.enumeration.SocialRoles;
import br.com.cwi.colaai.service.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author erico.loewe
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioServico usuarioServico;

    @Override
    public InformacoesUsuarioAtual loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioViewModel usuario = usuarioServico.buscarAutorizadoPorEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("Usuario %s não encontrado", username));
        }
        return new InformacoesUsuarioAtual(username, usuario.getSenha(), SocialRoles.valuesToList(), usuario);
    }

    public InformacoesUsuarioAtual getInformacoesUsuarioAtual() {
        return (InformacoesUsuarioAtual) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
