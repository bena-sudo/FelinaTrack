package com.pfc.felinatrack_back.src.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.model.db.IssueDb;
import com.pfc.felinatrack_back.model.dto.IssueEdit;
import com.pfc.felinatrack_back.model.dto.IssueInfo;
import com.pfc.felinatrack_back.model.dto.IssueList;

@Mapper
public interface IssueMapper {
    IssueMapper INSTANCE = Mappers.getMapper(IssueMapper.class);

    IssueEdit IssueDbToIssueEdit(IssueDb IssueDb);
    IssueDb IssueEditToIssueDb(IssueEdit IssueEdit);

    IssueDb IssueInfoToIssueDb(IssueInfo IssueInfo);
    IssueInfo IssueDbToIssueInfo(IssueDb IssueDb);

    @Mapping(target = "id", source = "id")
    void updateIssueDbFromIssueEdit(IssueEdit IssueEdit, @MappingTarget IssueDb IssueDb);

    List<IssueList> IssuesDbToIssuesList(List<IssueDb> IssuesDb);


    /**
     * Convierte una página de Issues en una respuesta paginada.
     */
    static PaginaResponse<IssueList> pageToPaginaResponse(
            Page<IssueDb> page,
            List<FiltroBusqueda> filters,
            List<String> sorting) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                IssueMapper.INSTANCE.IssuesDbToIssuesList(page.getContent()),
                filters,
                sorting
        );
    }
}
