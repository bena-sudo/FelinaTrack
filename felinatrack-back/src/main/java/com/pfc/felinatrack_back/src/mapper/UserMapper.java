package com.pfc.felinatrack_back.src.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.pfc.felinatrack_back.filters.model.FiltroBusqueda;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.model.db.UserDb;
import com.pfc.felinatrack_back.model.dto.UserEdit;
import com.pfc.felinatrack_back.model.dto.UserInfo;
import com.pfc.felinatrack_back.model.dto.UserList;


@Mapper(uses = RoleMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roles")
    UserInfo userDbToUserInfo(UserDb userDb);

    List<UserList> usersDbToUsersList(List<UserDb> usersDb);

    /**
     * Convierte una página de users en una respuesta paginada.
     */
    static PaginaResponse<UserList> pageToPaginaResponse(
            Page<UserDb> page,
            List<FiltroBusqueda> filters,
            List<String> sorting) {
        return new PaginaResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                UserMapper.INSTANCE.usersDbToUsersList(page.getContent()),
                filters,
                sorting
        );
    }

    // Devuelve un objeto de tipo 'UserEdit' a partir de un objeto de tipo 'UserDb'
    @Mapping(source = "roles", target = "roleIds")
    UserEdit userDbToUserEdit(UserDb userDb);

    // Devuelve un objeto de tipo 'UserDb' a partir de un objeto de tipo 'UserEdit'
    UserDb userEditToUserDb(UserEdit userEdit);

    // Actualiza un objeto de tipo 'UserDb' con los datos de un objeto de tipo 'UserEdit'
    @Mapping(source = "roleIds", target = "roles")
    void updateUserDbFromUserEdit(UserEdit userEdit, @MappingTarget UserDb userDb);

    List<UserInfo> usersDbToUserInfo(List<UserDb> usersDb);
    List<UserDb> usersListToUsersDb(List<UserList> usersList);
}