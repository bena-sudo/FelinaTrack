package com.joan.felinatrack_back.filters.specification.operacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.joan.felinatrack_back.filters.model.FiltroBusqueda;
import com.joan.felinatrack_back.filters.model.TipoOperacionBusqueda;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Estrategia para la operación de búsqueda "IGUAL".
 * <p>
 * Aplica un filtro que verifica si el valor de un atributo es igual al valor
 * proporcionado.
 * Incluye soporte especial para comparar fechas sin tener en cuenta la hora.
 */
public class IgualOperacionStrategy implements OperacionBusquedaStrategy {

    /**
     * Crea un predicado que aplica la condición "IGUAL" sobre el atributo
     * especificado.
     * <p>
     * Si el atributo es de tipo {@code LocalDateTime}, se compara únicamente la
     * fecha,
     * ignorando la hora.
     *
     * @param root            Entidad raíz sobre la que se aplica la consulta.
     * @param criteriaBuilder Constructor de criterios de JPA.
     * @param filtro          Objeto que contiene el atributo y el valor a filtrar.
     * @return Predicado configurado con la operación IGUAL.
     */
    @Override
    public Predicate crearPredicado(
            Root<?> root,
            CriteriaBuilder criteriaBuilder,
            FiltroBusqueda filtro) {

        // Si el atributo es LocalDateTime, se transforma para comparar solo la fecha
        if (root.get(filtro.getAtributo()).getJavaType().equals(java.time.LocalDateTime.class)) {
            LocalDate fecha = LocalDate.parse((CharSequence) filtro.getValor(),
                    DateTimeFormatter.ISO_DATE);

            // Utiliza la función DATE para truncar la fecha en la base de datos
            Expression<LocalDate> fechaConvertida = criteriaBuilder.function(
                    "DATE", LocalDate.class, root.get(filtro.getAtributo()));

            return criteriaBuilder.equal(fechaConvertida, fecha);
        }

        // Comparación estándar para otros tipos de datos
        return criteriaBuilder.equal(
                root.get(filtro.getAtributo()),
                filtro.getValor());
    }

    /**
     * Verifica si esta estrategia soporta la operación IGUAL.
     *
     * @param operacion Tipo de operación a evaluar.
     * @return {@code true} si la operación es IGUAL, de lo contrario {@code false}.
     */
    @Override
    public boolean soportaOperacion(TipoOperacionBusqueda operacion) {
        return operacion == TipoOperacionBusqueda.IGUAL;
    }
}
