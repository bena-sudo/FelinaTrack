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
import com.pfc.felinatrack_back.model.db.ResponsibleDb;
import com.pfc.felinatrack_back.model.dto.ResponsibleEdit;
import com.pfc.felinatrack_back.model.dto.ResponsibleInfo;
import com.pfc.felinatrack_back.model.dto.ResponsibleList;
import com.pfc.felinatrack_back.repository.ResponsibleRepository;
import com.pfc.felinatrack_back.src.ResponsibleService;
import com.pfc.felinatrack_back.src.mapper.ResponsibleMapper;


@Service
public class ResponsibleServiceImpl implements ResponsibleService{
    
    @Autowired
    private ResponsibleRepository ResponsibleRepository;

    @Autowired
    private PaginationFactory paginationFactory;

    @Autowired
    private PeticionListadoFiltradoConverter peticionConverter;


    @Override
    public PaginaResponse<ResponsibleList> findAll(List<String> filter, int page, int size, List<String> sort) 
            throws FiltroException {
        /** 'peticionConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);// En vez de hacer 2 veces el filtrado llamamos al método que lo centraliza
    }

    @SuppressWarnings("null")
    @Override
    public PaginaResponse<ResponsibleList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            // Configurar ordenamiento
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            // Configurar criterio de filtrado con SpecifiResponsibleion
            Specification<ResponsibleDb> filtrosBusquedaSpecifiResponsibleion = new FiltroBusquedaSpecification<ResponsibleDb>(
                peticionListadoFiltrado.getListaFiltros());
            // Filtrar y ordenar: puede producir cualquier de los errores controlados en el Responsiblech
            Page<ResponsibleDb> page = ResponsibleRepository.findAll(filtrosBusquedaSpecifiResponsibleion, pageable);
            //Devolver respuesta
            return ResponsibleMapper.pageToPaginaResponse(
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
    public ResponsibleEdit save(ResponsibleEdit ResponsibleEdit) {
        if (ResponsibleEdit == null) {
            throw new IllegalArgumentException("El responsable no puede ser nulo");
        }
        ResponsibleDb ResponsibleDb = ResponsibleMapper.INSTANCE.ResponsibleEditToResponsibleDb(ResponsibleEdit);
        ResponsibleDb savaedResponsibleDb = ResponsibleRepository.save(ResponsibleDb);
        return ResponsibleMapper.INSTANCE.ResponsibleDbToResponsibleEdit(savaedResponsibleDb);
    }

    @Override
    public void delete(Long id) {
        if (ResponsibleRepository.existsById(id)) {
            ResponsibleRepository.deleteById(id);
        }
    }

    @Override
    public ResponsibleEdit update(Long id, ResponsibleEdit ResponsibleEdit) {

        ResponsibleDb existingEntity = ResponsibleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("RESPONSIBLE_NOT_FOUND_FOR_UPDATE", 
                "No se puede actualizar. El responsable con ID " + id + " no existe"));

        ResponsibleEdit.setId(id);

        ResponsibleMapper.INSTANCE.updateResponsibleDbFromResponsibleEdit(ResponsibleEdit, existingEntity);

        ResponsibleDb updatedEntity = ResponsibleRepository.save(existingEntity);
        return ResponsibleMapper.INSTANCE.ResponsibleDbToResponsibleEdit(updatedEntity);
    }
    

    @Override
    public ResponsibleInfo read(Long id) {
        ResponsibleDb entity = ResponsibleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("RESPONSIBLE_NOT_FOUND", 
                "No se encontró el responsable con ID " + id));
        return ResponsibleMapper.INSTANCE.ResponsibleDbToResponsibleInfo(entity);
    }

}
