package com.joan.felinatrack_back.filters.specification.operacion;

import com.joan.felinatrack_back.filters.model.FiltroBusqueda;
import com.joan.felinatrack_back.filters.model.TipoOperacionBusqueda;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Estrategia para la operación de búsqueda "MENOR QUE".
 * <p>
 * Aplica un filtro que verifica si el valor de un atributo es menor que el
 * valor proporcionado.
 */
public class MenorQueOperacionStrategy implements OperacionBusquedaStrategy {

    /**
     * Crea un predicado que aplica la condición "MENOR QUE" sobre el atributo
     * especificado.
     *
     * @param root            Entidad raíz sobre la que se aplica la consulta.
     * @param criteriaBuilder Constructor de criterios de JPA.
     * @param filtro          Objeto que contiene el atributo y el valor a filtrar.
     * @return Predicado configurado con la operación MENOR QUE.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Predicate crearPredicado(
            Root<?> root,
            CriteriaBuilder criteriaBuilder,
            FiltroBusqueda filtro) {
        return criteriaBuilder.lessThan(
                root.get(filtro.getAtributo()),
                (Comparable) filtro.getValor());
    }

    /**
     * Verifica si esta estrategia soporta la operación MENOR QUE.
     *
     * @param operacion Tipo de operación a evaluar.
     * @return {@code true} si la operación es MENOR QUE, de lo contrario
     *         {@code false}.
     */
    @Override
    public boolean soportaOperacion(TipoOperacionBusqueda operacion) {
        return operacion == TipoOperacionBusqueda.MENOR_QUE;
    }
}
