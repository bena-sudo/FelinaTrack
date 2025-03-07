package com.pfc.felinatrack_back.src;

import java.util.List;

import com.pfc.felinatrack_back.exception.FiltroException;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.filters.model.PeticionListadoFiltrado;
import com.pfc.felinatrack_back.model.dto.IssueEdit;
import com.pfc.felinatrack_back.model.dto.IssueInfo;
import com.pfc.felinatrack_back.model.dto.IssueList;


public interface IssueService {


        public IssueEdit save(IssueEdit IssueEdit);
        public IssueInfo read(Long id);
        public IssueEdit update(Long id,IssueEdit IssueEdit);
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
        public PaginaResponse<IssueList> findAll(List<String> filter, int page, int size, List<String> sort) throws FiltroException;
        /**
         * Busca jugadores aplicando filtros, paginación y ordenación a partir de una petición estructurada.
         * 
         * @param peticionListadoFiltrado Objeto que encapsula los criterios de búsqueda (nº pagina, tamaño de la página, lista de filtros y lista de ordenaciones)
         * @return PaginaResponse con la lista de jugadores filtrada y paginada
         * @throws FiltroException Si hay errores en los filtros o la ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')
         */
        public PaginaResponse<IssueList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException;


}
