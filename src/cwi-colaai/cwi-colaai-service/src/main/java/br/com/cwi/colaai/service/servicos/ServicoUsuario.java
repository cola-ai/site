/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.service.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author erico.loewe
 */
@Service
public class ServicoUsuario {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    
    public UsuarioViewModel buscarPorEmail(String email) {
        return usuarioRepositorio.findOneByEmail(email).toUsuarioViewModel();
    }
    
    public void criar(UsuarioViewModel usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
