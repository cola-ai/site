/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Token;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author diuly.barreto
 */
public interface TokenRepositorio extends CrudRepository<Token, Long> {
    public Token findByValor(String token);
}
