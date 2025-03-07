package com.pfc.felinatrack_back.src.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.model.db.ActionDb;
import com.pfc.felinatrack_back.model.dto.ActionEdit;
import com.pfc.felinatrack_back.model.dto.ActionInfo;
import com.pfc.felinatrack_back.model.dto.ActionList;

@Mapper
public interface ActionMapper {
    ActionMapper INSTANCE = Mappers.getMapper(ActionMapper.class);

    ActionEdit ActionDbToActionEdit(ActionDb ActionDb);
    ActionDb ActionEditToActionDb(ActionEdit ActionEdit);

    ActionDb ActionInfoToActionDb(ActionInfo ActionInfo);
    ActionInfo ActionDbToActionInfo(ActionDb ActionDb);

    @Mapping(target = "id", source = "id")
    void updateActionDbFromActionEdit(ActionEdit ActionEdit, @MappingTarget ActionDb ActionDb);

    List<ActionList> ActionsDbToActionsList(List<ActionDb> ActionsDb);


    /**
     * Convierte una página de Actions en una respuesta paginada.
     */
    static PaginaResponse<ActionList> pageToPaginaResponse(
            Page<ActionDb> page,
            List<FiltroBusqueda> filters,
            List<String> sorting) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                ActionMapper.INSTANCE.ActionsDbToActionsList(page.getContent()),
                filters,
                sorting
        );
    }
}
