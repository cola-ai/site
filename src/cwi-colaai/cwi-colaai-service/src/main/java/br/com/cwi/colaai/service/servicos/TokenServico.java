/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.StatusToken;
import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.service.repositorios.TokenRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author diuly.barreto
 */
@Service
public class TokenServico {
    
    @Autowired
    TokenRepositorio tokenRepositorio;
    
    public String criarToken(Usuario usuario) {
        Token token = new Token();
        token.setUsuario(usuario);
        token.setValor(gerarValorToken());
        token.setStatus(StatusToken.PENDENTE);
        tokenRepositorio.save(token);
        
        return token.getValor();
    }
    
    public Token BuscarPorValorToken(String token) {
        return tokenRepositorio.findByValor(token);
    }
    
    private String gerarValorToken() {
        return new BCryptPasswordEncoder().encode(new Date().toString());
    }
}
