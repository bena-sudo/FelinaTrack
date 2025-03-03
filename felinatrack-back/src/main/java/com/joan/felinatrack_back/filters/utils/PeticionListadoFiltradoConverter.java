package com.joan.felinatrack_back.filters.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.joan.felinatrack_back.exception.FiltroException;
import com.joan.felinatrack_back.filters.model.FiltroBusqueda;
import com.joan.felinatrack_back.filters.model.PeticionListadoFiltrado;

/**
 * Componente encargado de convertir los parámetros recibidos en una petición
 * a un objeto {@link PeticionListadoFiltrado}.
 */
@Component
public class PeticionListadoFiltradoConverter {

    private final FiltroBusquedaFactory filtroBusquedaFactory;

    /**
     * Constructor que inyecta la fábrica de filtros de búsqueda.
     *
     * @param filtroBusquedaFactory Fábrica para la creación de filtros a partir de
     *                              texto.
     */
    public PeticionListadoFiltradoConverter(FiltroBusquedaFactory filtroBusquedaFactory) {
        this.filtroBusquedaFactory = filtroBusquedaFactory;
    }

    /**
     * Convierte los parámetros individuales de una solicitud en un objeto
     * {@link PeticionListadoFiltrado}.
     *
     * @param filter Lista de filtros como cadenas con formato específico.
     * @param page   Número de página solicitado.
     * @param size   Tamaño de página.
     * @param sort   Lista de criterios de ordenación.
     * @return Objeto {@link PeticionListadoFiltrado} construido con los datos
     *         proporcionados.
     * @throws FiltroException Si alguno de los filtros no tiene el formato
     *                         correcto.
     */
    public PeticionListadoFiltrado convertFromParams(
            List<String> filter,
            int page,
            int size,
            List<String> sort) throws FiltroException {

        // Se procesan los filtros utilizando la fábrica y se encapsulan junto con los
        // demás parámetros.
        List<FiltroBusqueda> filtros = filtroBusquedaFactory.crearListaFiltrosBusqueda(filter);

        return new PeticionListadoFiltrado(filtros, page, size, sort);
    }
}
