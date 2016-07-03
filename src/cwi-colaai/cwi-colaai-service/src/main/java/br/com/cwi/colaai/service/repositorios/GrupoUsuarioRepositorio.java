/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.GrupoUsuario;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Alycio
 */
public interface GrupoUsuarioRepositorio extends CrudRepository<GrupoUsuario, Long> {
    
}
