package com.pfc.felinatrack_back.src.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pfc.felinatrack_back.model.db.ActionDb;
import com.pfc.felinatrack_back.model.dto.ActionEdit;
import com.pfc.felinatrack_back.model.dto.ActionInfo;
import com.pfc.felinatrack_back.model.dto.ActionList;
import com.pfc.felinatrack_back.repository.ActionRepository;
import com.pfc.felinatrack_back.src.ActionService;
import com.pfc.felinatrack_back.src.mapper.ActionMapper;


@Service
public class ActionServiceImpl implements ActionService{
    
    @Autowired
    private ActionRepository ActionRepository;

    @Autowired
    private PaginationFactory paginationFactory;

    @Autowired
    private PeticionListadoFiltradoConverter peticionConverter;


    @Override
    public PaginaResponse<ActionList> findAll(List<String> filter, int page, int size, List<String> sort) 
            throws FiltroException {
        /** 'peticionConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);// En vez de hacer 2 veces el filtrado llamamos al método que lo centraliza
    }

    @SuppressWarnings("null")
    @Override
    public PaginaResponse<ActionList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            // Configurar ordenamiento
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            // Configurar criterio de filtrado con SpecifiActionion
            Specification<ActionDb> filtrosBusquedaSpecifiActionion = new FiltroBusquedaSpecification<ActionDb>(
                peticionListadoFiltrado.getListaFiltros());
            // Filtrar y ordenar: puede producir cualquier de los errores controlados en el Actionch
            Page<ActionDb> page = ActionRepository.findAll(filtrosBusquedaSpecifiActionion, pageable);
            //Devolver respuesta
            return ActionMapper.pageToPaginaResponse(
                page,
                peticionListadoFiltrado.getListaFiltros(), 
                peticionListadoFiltrado.getSort());
        } catch (JpaSystemException e) {
            String cause="";
            if  (e.getRootCause()!=null){
                if (e.getCause().getMessage()!=null)
                    cause= e.getRootCause().getMessage();
            }
            throw new FiltroException("BAD_OPERATOR_FILTER",
                    "Error: No se puede realizar esa operación sobre el atributo por el tipo de dato", e.getMessage()+":"+cause);
        } catch (PropertyReferenceException e) {
            throw new FiltroException("BAD_ATTRIBUTE_ORDER",
                    "Error: No existe el nombre del atributo de ordenación en la tabla", e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) {
            throw new FiltroException("BAD_ATTRIBUTE_FILTER", "Error: Posiblemente no existe el atributo en la tabla",
                    e.getMessage());
        }
    }



    // GUARDAR - BORRAR - ACTUALITZAR - LEER

    @Override
    public ActionEdit save(ActionEdit ActionEdit) {
        if (ActionEdit == null) {
            throw new IllegalArgumentException("La acción no puede ser nula");
        }
        ActionDb ActionDb = ActionMapper.INSTANCE.ActionEditToActionDb(ActionEdit);
        ActionDb savaedActionDb = ActionRepository.save(ActionDb);
        return ActionMapper.INSTANCE.ActionDbToActionEdit(savaedActionDb);
    }

    @Override
    public void delete(Long id) {
        if (ActionRepository.existsById(id)) {
            ActionRepository.deleteById(id);
        }
    }

    @Override
    public ActionEdit update(Long id, ActionEdit ActionEdit) {

        ActionDb existingEntity = ActionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ACTION_NOT_FOUND_FOR_UPDATE", 
                "No se puede actualizar. La acción con ID " + id + " no existe"));

        ActionEdit.setId(id);

        ActionMapper.INSTANCE.updateActionDbFromActionEdit(ActionEdit, existingEntity);

        ActionDb updatedEntity = ActionRepository.save(existingEntity);
        return ActionMapper.INSTANCE.ActionDbToActionEdit(updatedEntity);
    }
    

    @Override
    public ActionInfo read(Long id) {
        ActionDb entity = ActionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ACTION_NOT_FOUND", 
                "No se encontró la acción con ID " + id));
        return ActionMapper.INSTANCE.ActionDbToActionInfo(entity);
    }

}
