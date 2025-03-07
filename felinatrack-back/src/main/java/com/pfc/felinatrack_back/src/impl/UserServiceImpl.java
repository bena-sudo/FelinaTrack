package com.pfc.felinatrack_back.src.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.pfc.felinatrack_back.exception.EntityNotFoundException;
import com.pfc.felinatrack_back.exception.FiltroException;
import com.pfc.felinatrack_back.filters.model.PaginaResponse;
import com.pfc.felinatrack_back.filters.model.PeticionListadoFiltrado;
import com.pfc.felinatrack_back.filters.specification.FiltroBusquedaSpecification;
import com.pfc.felinatrack_back.filters.utils.PaginationFactory;
import com.pfc.felinatrack_back.filters.utils.PeticionListadoFiltradoConverter;
import com.pfc.felinatrack_back.model.db.RoleDb;
import com.pfc.felinatrack_back.model.db.UserDb;
import com.pfc.felinatrack_back.model.dto.LoginUser;
import com.pfc.felinatrack_back.model.dto.PageDto;
import com.pfc.felinatrack_back.model.dto.UserEdit;
import com.pfc.felinatrack_back.model.dto.UserList;
import com.pfc.felinatrack_back.repository.RoleRepository;
import com.pfc.felinatrack_back.repository.UserRepository;
import com.pfc.felinatrack_back.src.UserService;
import com.pfc.felinatrack_back.src.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PaginationFactory paginationFactory;
    private final PeticionListadoFiltradoConverter requestConverter;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PaginationFactory paginationFactory,
            PeticionListadoFiltradoConverter requestConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.paginationFactory = paginationFactory;
        this.requestConverter = requestConverter;
    }

    @Override
    public PaginaResponse<UserList> findAll(List<String> filter, int page, int size, List<String> sort)
            throws FiltroException {
        /** 'requestConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado request = requestConverter.convertFromParams(filter, page, size, sort);
        return findAll(request); // En vez de hacer 2 veces el filtrado llamamos al método que lo centraliza
    }

    @SuppressWarnings("null")
    @Override
    public PaginaResponse<UserList> findAll(PeticionListadoFiltrado request) throws FiltroException {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            Pageable pageable = paginationFactory.createPageable(request);
            Specification<UserDb> searchFilters = new FiltroBusquedaSpecification<UserDb>(
                request.getListaFiltros());
            Page<UserDb> page = userRepository.findAll(searchFilters, pageable);
            return UserMapper.pageToPaginaResponse(
                page,
                request.getListaFiltros(),
                request.getSort());
        } catch (JpaSystemException e) {
            String cause = "";
            if (e.getRootCause() != null && e.getCause().getMessage() != null) {
                cause = e.getRootCause().getMessage();
            }
            throw new FiltroException("BAD_OPERATOR_FILTER",
                    "Error: No se puede realizar esa operación sobre el atributo por el tipo de dato",
                    e.getMessage() + ":" + cause);
        } catch (PropertyReferenceException e) {
            throw new FiltroException("BAD_ATTRIBUTE_ORDER",
                    "Error: No existe el nombre del atributo de ordenación en la tabla", e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) {
            throw new FiltroException("BAD_ATTRIBUTE_FILTER",
                    "Error: Posiblemente no existe el atributo en la tabla", e.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkLogin(LoginUser loginUser) {
        Optional<UserDb> optionalUser = userRepository.findByEmail(loginUser.getEmail());
        if (optionalUser.isPresent()) {
            UserDb user = optionalUser.get();
            return user.getPassword().equals(loginUser.getPassword());
        }
        return false;
    }

    @Override
    public PageDto<UserList> findAll(Pageable paging) {
        Page<UserDb> userPage = userRepository.findAll(paging);
        return new PageDto<>(
            userPage.getNumber(),
            userPage.getSize(),
            userPage.getTotalElements(),
            userPage.getTotalPages(),
            UserMapper.INSTANCE.usersDbToUsersList(userPage.getContent()),
            userPage.getSort());
    }

    @Override
    public UserEdit read(Long id) {
        UserDb entity = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND",
                "No se encontró el user con ID " + id));
        return UserMapper.INSTANCE.userDbToUserEdit(entity);
    }

    @Override
    public UserEdit update(Long id, UserEdit userEdit) {
        UserDb existingEntity = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND_FOR_UPDATE",
                "No se puede actualizar. El user con ID " + id + " no existe"));

        userEdit.setId(id);
        UserMapper.INSTANCE.updateUserDbFromUserEdit(userEdit, existingEntity);

        if (userEdit.getRoleIds() != null) {
            Set<RoleDb> roles = userEdit.getRoleIds().stream()
                .map(roleId -> roleRepository.findById(roleId)
                    .orElseThrow(() -> new EntityNotFoundException("ROLE_NOT_FOUND",
                            "No se encontró el rol con ID " + roleId)))
                .collect(Collectors.toSet());
            existingEntity.setRoles(roles);
        }

        UserDb updatedEntity = userRepository.save(existingEntity);
        return UserMapper.INSTANCE.userDbToUserEdit(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    // Asignar roles a un user
    @Override
    public UserDb assignRolesToUser(Long userId, List<Long> roleIds) {
        UserDb user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND",
                    "No se encontró el user con ID " + userId));

        Set<RoleDb> roles = roleIds.stream()
            .map(roleId -> roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("ROLE_NOT_FOUND",
                        "No se encontró el rol con ID " + roleId)))
            .collect(Collectors.toSet());

        user.setRoles(roles);
        return userRepository.save(user);
    }
}