package com.joan.felinatrack_back.filters.specification;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.joan.felinatrack_back.filters.model.FiltroBusqueda;
import com.joan.felinatrack_back.filters.specification.operacion.ContieneOperacionStrategy;
import com.joan.felinatrack_back.filters.specification.operacion.IgualOperacionStrategy;
import com.joan.felinatrack_back.filters.specification.operacion.MayorQueOperacionStrategy;
import com.joan.felinatrack_back.filters.specification.operacion.MenorQueOperacionStrategy;
import com.joan.felinatrack_back.filters.specification.operacion.OperacionBusquedaStrategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Implementación de la especificación de filtro para realizar búsquedas
 * dinámicas
 * en entidades JPA utilizando operaciones de comparación como "igual",
 * "contiene", "mayor que", "menor que".
 * <p>
 * Esta clase permite construir una consulta JPA de manera flexible, basada en
 * los filtros y operaciones
 * proporcionados por el cliente.
 */
public class FiltroBusquedaSpecification<T> implements Specification<T> {

    private final List<FiltroBusqueda> filtrosBusqueda;
    private final List<OperacionBusquedaStrategy> estrategias;

    /**
     * Devuelve las estrategias predeterminadas para las operaciones de búsqueda.
     * Estas estrategias definen cómo se manejarán las operaciones de comparación
     * (por ejemplo, IGUAL, CONTIENE, MAYOR QUE, MENOR QUE).
     *
     * @return Lista de estrategias de operaciones de búsqueda.
     */
    private List<OperacionBusquedaStrategy> getDefaultStrategies() {
        return List.of(
                new IgualOperacionStrategy(),
                new ContieneOperacionStrategy(),
                new MayorQueOperacionStrategy(),
                new MenorQueOperacionStrategy());
    }

    /**
     * Constructor que inicializa la especificación con una lista de filtros de
     * búsqueda.
     *
     * @param filtrosBusqueda Lista de filtros de búsqueda a aplicar.
     */
    public FiltroBusquedaSpecification(List<FiltroBusqueda> filtrosBusqueda) {
        this.filtrosBusqueda = filtrosBusqueda;
        this.estrategias = getDefaultStrategies();
    }

    /**
     * Constructor que inicializa la especificación con una lista de filtros de
     * búsqueda
     * y una lista de estrategias personalizadas para las operaciones de búsqueda.
     *
     * @param filtrosBusqueda Lista de filtros de búsqueda a aplicar.
     * @param estrategias     Lista de estrategias personalizadas de operaciones de
     *                        búsqueda.
     */
    public FiltroBusquedaSpecification(List<FiltroBusqueda> filtrosBusqueda,
            List<OperacionBusquedaStrategy> estrategias) {
        this.filtrosBusqueda = filtrosBusqueda;
        this.estrategias = estrategias;
    }

    /**
     * Construye la especificación de búsqueda con una lista de filtros
     * proporcionados.
     *
     * @param filtros Lista de filtros de búsqueda a aplicar.
     * @return Una especificación que representa la consulta de búsqueda.
     */
    public Specification<T> build(List<FiltroBusqueda> filtros) {
        if (filtros == null || filtros.isEmpty()) {
            // Si no hay filtros, retornamos una conjunción vacía (sin condiciones).
            return (root, query, cb) -> cb.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            // Creamos la lista de predicados a partir de los filtros proporcionados.
            List<Predicate> predicados = filtros.stream()
                    .map(filtro -> crearPredicado(root, criteriaBuilder, filtro))
                    .collect(Collectors.toList());

            // Si hay predicados, los combinamos usando 'AND'. De lo contrario, retornamos
            // una conjunción vacía.
            return predicados.isEmpty()
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.and(predicados.toArray(Predicate[]::new));
        };
    }

    /**
     * Crea un predicado de búsqueda basado en el filtro proporcionado.
     *
     * @param root            Entidad raíz de la consulta.
     * @param criteriaBuilder Constructor de criterios utilizado para crear las
     *                        condiciones de la consulta.
     * @param filtro          Filtro de búsqueda que contiene el atributo, operación
     *                        y valor.
     * @return Un predicado que representa la condición de búsqueda.
     */
    private Predicate crearPredicado(Root<T> root, CriteriaBuilder criteriaBuilder, FiltroBusqueda filtro) {
        // Filtramos las estrategias para encontrar la que soporta la operación
        // solicitada.
        return estrategias.stream()
                .filter(estrategia -> estrategia.soportaOperacion(filtro.getOperacion()))
                .findFirst()
                .map(estrategia -> estrategia.crearPredicado(root, criteriaBuilder, filtro))
                .orElseThrow(() -> new UnsupportedOperationException(
                        "Operador de filtro no permitido: " + filtro.getOperacion()));
    }

    /**
     * Implementación del método de la interfaz {@link Specification} para construir
     * la consulta.
     * 
     * @param root            Raíz de la entidad en la consulta.
     * @param query           La consulta de Criteria (se ignora en este caso).
     * @param criteriaBuilder Constructor de criterios utilizado para construir la
     *                        consulta.
     * @return Un predicado que representa la consulta con los filtros aplicados.
     */
    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @Nullable CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder) {
        if (filtrosBusqueda == null || filtrosBusqueda.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        // Creamos la lista de predicados a partir de los filtros proporcionados.
        List<Predicate> predicados = filtrosBusqueda.stream()
                .map(filtro -> crearPredicado(root, criteriaBuilder, filtro))
                .collect(Collectors.toList());

        // Si hay predicados, los combinamos usando 'AND'. De lo contrario, retornamos
        // una conjunción vacía.
        return predicados.isEmpty()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.and(predicados.toArray(Predicate[]::new));
    }
}
