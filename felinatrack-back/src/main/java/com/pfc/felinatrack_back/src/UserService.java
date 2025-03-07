package com.pfc.felinatrack_back.src;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.pfc.felinatrack_back.exception.FiltroException;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.filters.model.PeticionListadoFiltrado;
import com.pfc.felinatrack_back.model.db.UserDb;
import com.pfc.felinatrack_back.model.dto.LoginUser;
import com.pfc.felinatrack_back.model.dto.PageDto;
import com.pfc.felinatrack_back.model.dto.UserEdit;
import com.pfc.felinatrack_back.model.dto.UserList;

import io.micrometer.common.lang.NonNull;

public interface UserService {

    boolean existsByEmail(@NonNull String email);
    boolean checkLogin(@NonNull LoginUser loginUsuario);

    PageDto<UserList> findAll(Pageable paging);

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
    PaginaResponse<UserList> findAll(List<String> filter, int page, int size, List<String> sort) throws FiltroException;

    /**
     * Busca jugadores aplicando filtros, paginación y ordenación a partir de una petición estructurada.
     * 
     * @param peticionListadoFiltrado Objeto que encapsula los criterios de búsqueda (nº pagina, tamaño de la página, lista de filtros y lista de ordenaciones)
     * @return PaginaResponse con la lista de jugadores filtrada y paginada
     * @throws FiltroException Si hay errores en los filtros o la ordenación (errorCodes: 'BAD_OPERATOR_FILTER','BAD_ATTRIBUTE_ORDER','BAD_ATTRIBUTE_FILTER','BAD_FILTER')
     */
    PaginaResponse<UserList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException;

    // CRUD operations
    UserEdit read(Long id);
    UserEdit update(Long id, UserEdit userEdit);
    void delete(Long id);

    // Assign roles to a user
    UserDb assignRolesToUser(Long userId, List<Long> roleIds);
}