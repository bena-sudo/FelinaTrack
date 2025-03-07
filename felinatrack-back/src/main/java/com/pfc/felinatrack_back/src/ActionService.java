package com.pfc.felinatrack_back.src;

import java.util.List;

import com.pfc.felinatrack_back.exception.FiltroException;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.filters.model.PeticionListadoFiltrado;
import com.pfc.felinatrack_back.model.dto.ActionEdit;
import com.pfc.felinatrack_back.model.dto.ActionInfo;
import com.pfc.felinatrack_back.model.dto.ActionList;


public interface ActionService {


        public ActionEdit save(ActionEdit ActionEdit);
        public ActionInfo read(Long id);
        public ActionEdit update(Long id,ActionEdit ActionEdit);
        public void delete(Long id);


        /**
         * Busca jugadores aplicando filtros, paginación y ordenación a partir de parámetros individuales.
         * 
         * @param filter Array de strings con los filtros en formato "campo:operador:valor"
         * @param page Número de página (zero-based)
         * @param size Tamaño de cada página
         * @param sort Array con criterios de ordenación en formato "campo", "campo,asc" o "campo,desc"
         * @return PaginaResponse con la lista de jugadores filtrada y paginada
         * @throws FiltroException Si hay errores en los filtros o la ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')
         */
        public PaginaResponse<ActionList> findAll(List<String> filter, int page, int size, List<String> sort) throws FiltroException;
        /**
         * Busca jugadores aplicando filtros, paginación y ordenación a partir de una petición estructurada.
         * 
         * @param peticionListadoFiltrado Objeto que encapsula los criterios de búsqueda (nº pagina, tamaño de la página, lista de filtros y lista de ordenaciones)
         * @return PaginaResponse con la lista de jugadores filtrada y paginada
         * @throws FiltroException Si hay errores en los filtros o la ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')
         */
        public PaginaResponse<ActionList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException;


}
