/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Érico de Souza Loewe
 */
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
    
    Usuario findOneByEmail(String email);
}