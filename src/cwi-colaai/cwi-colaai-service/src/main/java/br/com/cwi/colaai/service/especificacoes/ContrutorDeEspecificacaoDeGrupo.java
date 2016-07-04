
package br.com.cwi.colaai.service.especificacoes;

import br.com.cwi.colaai.entity.Grupo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
 *
 * @author Érico de Souza Loewe
 */
public class ContrutorDeEspecificacaoDeGrupo {
    
    private final List<CriteriosDeBusca> parametros;
 
    public ContrutorDeEspecificacaoDeGrupo() {
        parametros = new ArrayList<>();
    }
 
    public ContrutorDeEspecificacaoDeGrupo with(String chave, String operacao, Object valor) {
        parametros.add(new CriteriosDeBusca(chave, operacao, valor));
        return this;
    }
 
    public Specification<Grupo> build() {
        if (parametros.isEmpty()) {
            return null;
        }
 
        List<Specification<Grupo>> especs = new ArrayList<>();
        
        parametros.stream().forEach((param) -> {
            especs.add(new EspecificacaoDeGrupo(param));
        });
 
        Specification<Grupo> result = especs.get(0);
        for (int i = 1; i < especs.size(); i++) {
            result = Specifications.where(result).and(especs.get(i));
        }
        return result;
    }
}
