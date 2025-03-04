package com.pfc.felinatrack_back.filters.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud para obtener una lista filtrada de
 * elementos con soporte para paginación y ordenación.
 * 
 * Esta clase se utiliza para capturar los parámetros de entrada en las
 * solicitudes de filtrado, incluyendo los filtros aplicados, el número de
 * página, el tamaño de la página y los criterios de ordenación.
 * 
 * @param listaFiltros Filtros aplicados para la búsqueda de los elementos.
 * @param page         Número de la página solicitada.
 * @param size         Tamaño de la página (número de elementos por página).
 * @param sort         Criterios de ordenación para los resultados (por ejemplo,
 *                     "nombre asc", "fecha desc").
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeticionListadoFiltrado implements Serializable {

    private static final long serialVersionUID = 1L; // Número de versión para serialización

    /**
     * Lista de filtros que se aplican en la búsqueda de los elementos.
     * Cada filtro tiene un atributo, una operación y un valor.
     */
    private List<FiltroBusqueda> listaFiltros;

    /**
     * Número de la página solicitada. Este valor se utiliza para la paginación de
     * los resultados.
     */
    private Integer page;

    /**
     * Tamaño de la página solicitada. Define el número de elementos que se
     * devolverán por página.
     */
    private Integer size;

    /**
     * Lista de criterios de ordenación. Cada criterio se especifica como una cadena
     * en el formato "atributo dirección" (por ejemplo, "nombre asc", "fecha desc").
     */
    private List<String> sort;
}
