package com.pfc.felinatrack_back.filters.specification.operacion;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.TipoOperacionBusqueda;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Estrategia para la operación de búsqueda "CONTIENTE".
 * <p>
 * Aplica un filtro que verifica si el valor de un atributo contiene una
 * subcadena específica.
 */
public class ContieneOperacionStrategy implements OperacionBusquedaStrategy {

    /**
     * Crea un predicado que aplica la condición "LIKE" sobre el atributo
     * especificado,
     * buscando coincidencias parciales que contengan el valor proporcionado.
     *
     * @param root            Entidad raíz sobre la que se aplica la consulta.
     * @param criteriaBuilder Constructor de criterios de JPA.
     * @param filtro          Objeto que contiene el atributo y el valor a filtrar.
     * @return Predicado configurado con la operación LIKE.
     */
    @Override
    public Predicate crearPredicado(
            Root<?> root,
            CriteriaBuilder criteriaBuilder,
            FiltroBusqueda filtro) {
        return criteriaBuilder.like(
                root.get(filtro.getAtributo()), "%" + filtro.getValor() + "%");
    }

    /**
     * Verifica si esta estrategia soporta la operación CONTIENE.
     *
     * @param operacion Tipo de operación a evaluar.
     * @return {@code true} si la operación es CONTIENE, de lo contrario
     *         {@code false}.
     */
    @Override
    public boolean soportaOperacion(TipoOperacionBusqueda operacion) {
        return operacion == TipoOperacionBusqueda.CONTIENE;
    }
}
