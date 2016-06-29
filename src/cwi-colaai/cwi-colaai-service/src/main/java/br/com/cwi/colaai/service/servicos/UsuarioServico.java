/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.RedeSocial;
import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author erico.loewe
 */
@Service
public class UsuarioServico {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    PessoaServico pessoaServico;
    
    @Autowired
    ImagemServico imagemServico;
    
    @Autowired
    TokenServico tokenServico;
    
    @Autowired
    MailServico mailServico;

    public UsuarioViewModel buscarPorEmail(String email) {
         return usuarioRepositorio.findOneByEmail(email).toUsuarioViewModel();
     }
    
    public void criar(UsuarioViewModel usuarioModel, MultipartFile file) {

        // TODO: validar email único
        if (!emailExiste(usuarioModel.getEmail())) {
            Usuario usuario = new Usuario();
            usuario.setEmail(usuarioModel.getEmail());
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
            usuario.setRedeSocial(RedeSocial.Nenhum);
            usuario.setEstaAutorizado(false);
            usuario.setImagem(imagemServico.SalvarImagem(file));
            usuario.setPessoa(pessoaServico.criar(usuarioModel));
            usuarioRepositorio.save(usuario);

            Token token = tokenServico.criarToken(usuario);
            mailServico.confirmarUsuario(usuario.getEmail(), "Confirme seu Usuario", token);
       } 
    }
    
    public void autorizarUsuario(Usuario usuario) {
        usuario.setEstaAutorizado(true);
        usuarioRepositorio.save(usuario);
    }
    
    public boolean emailExiste(String email) {
          return usuarioRepositorio.findOneByEmail(email) != null;
    }
}
