
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.RedeSocial;
import br.com.cwi.colaai.entity.Usuario;
import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cwi.colaai.service.repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author erico.loewe
 */
@Service
public class UsuarioServico {

    private static final String IMAGEM_USUARIO_PADRAO = "/media/img/anonymous.jpg";
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private PessoaServico pessoaServico;
    
    @Autowired
    private TokenServico tokenServico;
    
    private final ImagemServico imagemServico = new ImagemServico("usuario");

    public UsuarioViewModel buscarPorEmail(String email) {
        try {
            return usuarioRepositorio.findOneByEmail(email).toUsuarioViewModel();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean criar(UsuarioViewModel usuarioModel, ImagemViewModel imagem) {

        if (!emailExiste(usuarioModel.getEmail())) {
            Usuario usuario = new Usuario();
            usuario.setEmail(usuarioModel.getEmail());
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
            usuario.setRedeSocial(RedeSocial.Nenhum);
            usuario.setEstaAutorizado(false);
            usuario.setImagem(imagem.getNomeOriginal().isEmpty() ? IMAGEM_USUARIO_PADRAO : imagemServico.salvar(imagem));
            usuario.setPessoa(pessoaServico.criar(usuarioModel));
            usuarioRepositorio.save(usuario);
            
            tokenServico.enviarConfirmacaoAoUsuario(usuario);
            return true;
       } 
        
       return false;
    }
    
    public void alterarImagem(UsuarioViewModel usuarioViewModel, ImagemViewModel imagem){
        Usuario usuario = usuarioRepositorio.findById(usuarioViewModel.getIdUsuario());
        usuario.setImagem(imagem.getNomeOriginal().isEmpty() ? usuario.getImagem() : imagemServico.salvar(imagem));
        usuarioRepositorio.save(usuario);
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
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioViewModel.getSenha()));
        usuarioRepositorio.save(usuario);
    }
    
    public void liberarAlterarSenha(UsuarioViewModel usuarioViewModel){
        Usuario usuario = usuarioRepositorio.findOneByEmail(usuarioViewModel.getEmail());
        tokenServico.enviarRecuperacaoDeSenhaAoUsuario(usuario);
    }
    
    public UsuarioViewModel buscarAutorizadoPorEmail(String email) {
        Usuario usuario = usuarioRepositorio.findOneByEmailAndEstaAutorizadoTrue(email);
        return usuario != null ? usuario.toUsuarioViewModel() : null;
    }

    public Usuario buscarPorId(Long usuarioId) {
        return usuarioRepositorio.findOne(usuarioId);
    }
    
}
