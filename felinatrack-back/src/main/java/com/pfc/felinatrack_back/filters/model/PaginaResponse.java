package com.pfc.felinatrack_back.filters.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa la respuesta de una página de datos, incluyendo la
 * información de la paginación, los filtros aplicados y las ordenaciones.
 * 
 * Esta clase se utiliza para enviar una respuesta paginada, que incluye no solo
 * los datos, sino también la información adicional relevante como la cantidad
 * total de elementos, el número de la página, los filtros aplicados y las
 * ordenaciones.
 * 
 * @param <T> Tipo de los elementos contenidos en la página (p. ej., un listado
 *            de entidades).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginaResponse<T> {

    /**
     * El número de la página actual.
     */
    private int number;

    /**
     * El tamaño de la página (el número de elementos por página).
     */
    private int size;

    /**
     * El número total de elementos en la base de datos (sin tener en cuenta la
     * paginación).
     */
    private long totalElements;

    /**
     * El número total de páginas disponibles, calculado según el total de elementos
     * y el tamaño de página.
     */
    private int totalPages;

    /**
     * Los contenidos de la página actual. Esta lista contiene los elementos de tipo
     * T
     * correspondientes a la página solicitada.
     */
    private List<T> content;

    /**
     * La lista de filtros que fueron aplicados a la consulta para obtener los datos
     * de esta página.
     */
    private List<FiltroBusqueda> listaFiltros;

    /**
     * La lista de criterios de ordenación que se utilizaron en la consulta para
     * ordenar los datos.
     */
    private List<String> listaOrdenaciones;
}
