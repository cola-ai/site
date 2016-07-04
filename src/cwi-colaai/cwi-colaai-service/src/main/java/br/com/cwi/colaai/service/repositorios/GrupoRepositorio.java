/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.repositorios;

import br.com.cwi.colaai.entity.Grupo;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Alycio
 */
public interface GrupoRepositorio extends PagingAndSortingRepository<Grupo, Long> {
}
