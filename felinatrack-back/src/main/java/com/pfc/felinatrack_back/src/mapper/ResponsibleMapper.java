package com.pfc.felinatrack_back.src.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.model.db.ResponsibleDb;
import com.pfc.felinatrack_back.model.dto.ResponsibleEdit;
import com.pfc.felinatrack_back.model.dto.ResponsibleInfo;
import com.pfc.felinatrack_back.model.dto.ResponsibleList;

@Mapper
public interface ResponsibleMapper {
    ResponsibleMapper INSTANCE = Mappers.getMapper(ResponsibleMapper.class);

    ResponsibleEdit ResponsibleDbToResponsibleEdit(ResponsibleDb ResponsibleDb);
    ResponsibleDb ResponsibleEditToResponsibleDb(ResponsibleEdit ResponsibleEdit);

    ResponsibleDb ResponsibleInfoToResponsibleDb(ResponsibleInfo ResponsibleInfo);
    ResponsibleInfo ResponsibleDbToResponsibleInfo(ResponsibleDb ResponsibleDb);

    @Mapping(target = "id", source = "id")
    void updateResponsibleDbFromResponsibleEdit(ResponsibleEdit ResponsibleEdit, @MappingTarget ResponsibleDb ResponsibleDb);

    List<ResponsibleList> ResponsiblesDbToResponsiblesList(List<ResponsibleDb> ResponsiblesDb);


    /**
     * Convierte una página de Responsibles en una respuesta paginada.
     */
    static PaginaResponse<ResponsibleList> pageToPaginaResponse(
            Page<ResponsibleDb> page,
            List<FiltroBusqueda> filters,
            List<String> sorting) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                ResponsibleMapper.INSTANCE.ResponsiblesDbToResponsiblesList(page.getContent()),
                filters,
                sorting
        );
    }
}
