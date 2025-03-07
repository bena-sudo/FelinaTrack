package com.pfc.felinatrack_back.src.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.model.db.ColonyDb;
import com.pfc.felinatrack_back.model.dto.ColonyEdit;
import com.pfc.felinatrack_back.model.dto.ColonyInfo;
import com.pfc.felinatrack_back.model.dto.ColonyList;

@Mapper
public interface ColonyMapper {
    ColonyMapper INSTANCE = Mappers.getMapper(ColonyMapper.class);

    ColonyEdit ColonyDbToColonyEdit(ColonyDb ColonyDb);
    ColonyDb ColonyEditToColonyDb(ColonyEdit ColonyEdit);

    ColonyDb ColonyInfoToColonyDb(ColonyInfo ColonyInfo);
    ColonyInfo ColonyDbToColonyInfo(ColonyDb ColonyDb);

    @Mapping(target = "id", source = "id")
    void updateColonyDbFromColonyEdit(ColonyEdit ColonyEdit, @MappingTarget ColonyDb ColonyDb);

    List<ColonyList> ColonysDbToColonysList(List<ColonyDb> ColonysDb);


    /**
     * Convierte una página de Colonys en una respuesta paginada.
     */
    static PaginaResponse<ColonyList> pageToPaginaResponse(
            Page<ColonyDb> page,
            List<FiltroBusqueda> filters,
            List<String> sorting) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                ColonyMapper.INSTANCE.ColonysDbToColonysList(page.getContent()),
                filters,
                sorting
        );
    }
}
