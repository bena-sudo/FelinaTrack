package com.pfc.felinatrack_back.filters.specification.operacion;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.TipoOperacionBusqueda;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Estrategia para la operación de búsqueda "MAYOR QUE".
 * <p>
 * Aplica un filtro que verifica si el valor de un atributo es mayor que el
 * valor proporcionado.
 */
public class MayorQueOperacionStrategy implements OperacionBusquedaStrategy {

    /**
     * Crea un predicado que aplica la condición "MAYOR QUE" sobre el atributo
     * especificado.
     *
     * @param root            Entidad raíz sobre la que se aplica la consulta.
     * @param criteriaBuilder Constructor de criterios de JPA.
     * @param filtro          Objeto que contiene el atributo y el valor a filtrar.
     * @return Predicado configurado con la operación MAYOR QUE.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Predicate crearPredicado(
            Root<?> root,
            CriteriaBuilder criteriaBuilder,
            FiltroBusqueda filtro) {
        return criteriaBuilder.greaterThan(
                root.get(filtro.getAtributo()),
                (Comparable) filtro.getValor());
    }

    /**
     * Verifica si esta estrategia soporta la operación MAYOR QUE.
     *
     * @param operacion Tipo de operación a evaluar.
     * @return {@code true} si la operación es MAYOR QUE, de lo contrario
     *         {@code false}.
     */
    @Override
    public boolean soportaOperacion(TipoOperacionBusqueda operacion) {
        return operacion == TipoOperacionBusqueda.MAYOR_QUE;
    }
}
