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
        
    private static final String URL_BASE = "http://localhost:9090";
    
    @Autowired
    TokenRepositorio tokenRepositorio;
    
    @Autowired
    MailServico mailServico;
    
    public Token criarToken(Usuario usuario) {
        if(ehUsuarioValido(usuario)) {
            Token token = new Token();
            token.setUsuario(usuario);
            token.setValor(gerarValorToken());
            token.setStatus(StatusToken.PENDENTE);

            return tokenRepositorio.save(token); 
        }
        
        return null;
    }
    
    public Token buscarPorValorToken(String token) {
        return tokenRepositorio.findByValor(token);
    }
    
    private String gerarValorToken() {
        return new BCryptPasswordEncoder().encode(new Date().toString());
    }
    
    public void aprovarToken(Token token) {
        if(ehTokenValido(token)) {
            token.setStatus(StatusToken.APROVADO);
            tokenRepositorio.save(token);
        }
    }

    public void enviarConfirmacaoAoUsuario(Usuario usuario) {
        if(ehUsuarioValido(usuario)) {
            Token token = criarToken(usuario);
            mailServico.enviarEmail(usuario.getEmail(), "Confirme seu Usuario", mensagemConfirmacaoEmail(token));
        }
    }
    
    public void enviarRecuperacaoDeSenhaAoUsuario(Usuario usuario) {
        if(ehUsuarioValido(usuario)) {
            Token token = criarToken(usuario);
            mailServico.enviarEmail(usuario.getEmail(), "Recuperação de Email", mensagemAlteracaoSenha(token));
        }
    }
   
    private String mensagemConfirmacaoEmail(Token token) {
        return "<h2> Ola " + token.getUsuario().getPessoa().getNome() + "!</h2><br/>"
                + "Caso não tenha efetuado cadastro em nosso site, peço que desconsidere esta mensagem<br/>"
                + "Segue o link de confirmação da conta de e-mail: " + " <a href=" + URL_BASE + "/token/confirma?valor="
                + token.getValor() + "> Confirme seu Email!</a> <br/> Atenciosamente <br/> Equipe Cola ai!";

    }
    
    private String mensagemAlteracaoSenha(Token token) {
        return "<h2> Ola " + token.getUsuario().getPessoa().getNome() + "!</h2><br/>"
                + "Caso não tenha efetuado cadastro em nosso site, peço que desconsidere esta mensagem<br/>"
                + "Segue o link para recuperação de sua senha: " + " <a href=" + URL_BASE + "/token/recuperarSenha?valor="
                + token.getValor() + "> Recuperação de Senha</a> <br/> Atenciosamente <br/> Equipe Cola ai!";
    }
    
    public boolean ehUsuarioValido(Usuario usuario) {
        return usuario != null;
    }
    
    public boolean ehTokenValido(Token token) {
        return token != null;
    }
}
