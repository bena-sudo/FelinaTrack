package com.pfc.felinatrack_back.src.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.model.db.CatDb;
import com.pfc.felinatrack_back.model.dto.CatEdit;
import com.pfc.felinatrack_back.model.dto.CatInfo;
import com.pfc.felinatrack_back.model.dto.CatList;

@Mapper
public interface CatMapper {
    CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);

    CatEdit catDbToCatEdit(CatDb catDb);
    CatDb catEditToCatDb(CatEdit catEdit);

    CatDb catInfoToCatDb(CatInfo catInfo);
    CatInfo catDbToCatInfo(CatDb catDb);

    @Mapping(target = "id", source = "id")
    void updateCatDbFromCatEdit(CatEdit catEdit, @MappingTarget CatDb catDb);

    List<CatList> catsDbToCatsList(List<CatDb> catsDb);


    /**
     * Convierte una página de Cats en una respuesta paginada.
     */
    static PaginaResponse<CatList> pageToPaginaResponse(
            Page<CatDb> page,
            List<FiltroBusqueda> filters,
            List<String> sorting) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                CatMapper.INSTANCE.catsDbToCatsList(page.getContent()),
                filters,
                sorting
        );
    }
}
