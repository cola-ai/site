/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.RedeSocial;
import br.com.cwi.colaai.entity.Token;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    TokenServico tokenServico;
    
    ImagemServico imagemServico = new ImagemServico("usuario");

    public UsuarioViewModel buscarPorEmail(String email) {
         return usuarioRepositorio.findOneByEmail(email).toUsuarioViewModel();
     }
    
    public void criar(UsuarioViewModel usuarioModel, ImagemViewModel imagem) {

        // TODO: validar email único
        if (!emailExiste(usuarioModel.getEmail())) {
            Usuario usuario = new Usuario();
            usuario.setEmail(usuarioModel.getEmail());
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
            usuario.setRedeSocial(RedeSocial.Nenhum);
            usuario.setEstaAutorizado(false);
            usuario.setImagem(imagemServico.salvar(imagem));
            usuario.setPessoa(pessoaServico.criar(usuarioModel));
            usuarioRepositorio.save(usuario);
            
            tokenServico.enviarConfirmacaoAoUsuario(usuario);
       } 
    }
    
    public void autorizarUsuario(Usuario usuario) {
        usuario.setEstaAutorizado(true);
        usuarioRepositorio.save(usuario);
    }
    
    public boolean emailExiste(String email) {
          return usuarioRepositorio.findOneByEmail(email) != null;
    }
    
    public void alterarSenha(UsuarioViewModel usuarioViewModel){
        Usuario usuario = usuarioRepositorio.findById(usuarioViewModel.getIdUsuario());
        usuario.setSenha(usuarioViewModel.getSenha());
        usuarioRepositorio.save(usuario);
    }
}
