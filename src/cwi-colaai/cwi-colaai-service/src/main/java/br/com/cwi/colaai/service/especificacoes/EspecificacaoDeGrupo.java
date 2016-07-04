package br.com.cwi.colaai.service.especificacoes;

import br.com.cwi.colaai.entity.Grupo;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Érico de Souza Loewe
 */
public class EspecificacaoDeGrupo implements Specification<Grupo> {

    private CriteriosDeBusca criterio;

    public EspecificacaoDeGrupo() {
    }

    public EspecificacaoDeGrupo(CriteriosDeBusca criterio) {
        this.criterio = criterio;
    }
    
    @Override
    public Predicate toPredicate(Root<Grupo> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (criterio.getOperacao().equalsIgnoreCase(">")) {
            return cb.greaterThanOrEqualTo(
                    root.<String>get(criterio.getChave()), criterio.getValor().toString());
        } else if (criterio.getOperacao().equalsIgnoreCase("<")) {
            return cb.lessThanOrEqualTo(
                    root.<String>get(criterio.getChave()), criterio.getValor().toString());
        } else if (criterio.getOperacao().equalsIgnoreCase(":")) {
            if (root.get(criterio.getChave()).getJavaType() == String.class) {
                return cb.like(
                        root.<String>get(criterio.getChave()), "%" + criterio.getValor() + "%");
            } else {
                return cb.equal(root.get(criterio.getChave()), criterio.getValor());
            }
        }
        return null;
    }
}
