package com.joan.felinatrack_back.filters.specification.operacion;

import com.joan.felinatrack_back.filters.model.FiltroBusqueda;
import com.joan.felinatrack_back.filters.model.TipoOperacionBusqueda;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Interfaz que define las estrategias de operación para las búsquedas.
 * <p>
 * Cada implementación de esta interfaz corresponde a una operación de búsqueda
 * específica
 * (por ejemplo, "IGUAL", "MAYOR QUE", "MENOR QUE", etc.) y proporciona los
 * métodos necesarios
 * para construir los predicados de la consulta.
 */
public interface OperacionBusquedaStrategy {

    /**
     * Crea un predicado para una operación de búsqueda específica.
     * <p>
     * El predicado es utilizado para construir las condiciones de la consulta,
     * basándose en
     * el atributo, operación y valor del filtro proporcionado.
     *
     * @param root            La raíz de la consulta, que representa la entidad
     *                        sobre la que se aplica el filtro.
     * @param criteriaBuilder Constructor de criterios utilizado para crear las
     *                        condiciones de la consulta.
     * @param filtro          Filtro de búsqueda que contiene el atributo, operación
     *                        y valor a comparar.
     * @return Un predicado que representa la condición de la operación de búsqueda.
     */
    Predicate crearPredicado(
            Root<?> root,
            CriteriaBuilder criteriaBuilder,
            FiltroBusqueda filtro);

    /**
     * Indica si la estrategia soporta el tipo de operación especificado.
     * <p>
     * Cada estrategia tiene una operación específica que soporta, y este método se
     * utiliza para
     * determinar si una operación de búsqueda puede ser manejada por esta
     * estrategia.
     *
     * @param operacion Operación que se desea verificar si es soportada por la
     *                  estrategia.
     * @return {@code true} si la operación es soportada, {@code false} en caso
     *         contrario.
     */
    boolean soportaOperacion(TipoOperacionBusqueda operacion);
}
