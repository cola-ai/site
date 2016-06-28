/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.Pessoa;
import br.com.cwi.colaai.entity.view_model.UsuarioViewModel;
import br.com.cwi.colaai.service.repositorios.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diuly.barreto
 */
@Service
public class PessoaServico {
    
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    
    public Pessoa criar(UsuarioViewModel usuarioModel) {
       Pessoa pessoa = new Pessoa();
       pessoa.setNome(usuarioModel.getNome());
       pessoa.setSobrenome(usuarioModel.getSobrenome());
       pessoa.setSexo(usuarioModel.getSexo());
       pessoa.setTelefone(usuarioModel.getTelefone());
       
       return pessoaRepositorio.save(pessoa);
    }
}
