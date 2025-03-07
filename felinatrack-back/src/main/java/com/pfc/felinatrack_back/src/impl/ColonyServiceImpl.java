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
import com.pfc.felinatrack_back.model.db.ColonyDb;
import com.pfc.felinatrack_back.model.dto.ColonyEdit;
import com.pfc.felinatrack_back.model.dto.ColonyInfo;
import com.pfc.felinatrack_back.model.dto.ColonyList;
import com.pfc.felinatrack_back.repository.ColonyRepository;
import com.pfc.felinatrack_back.src.ColonyService;
import com.pfc.felinatrack_back.src.mapper.ColonyMapper;


@Service
public class ColonyServiceImpl implements ColonyService{
    
    @Autowired
    private ColonyRepository ColonyRepository;

    @Autowired
    private PaginationFactory paginationFactory;

    @Autowired
    private PeticionListadoFiltradoConverter peticionConverter;


    @Override
    public PaginaResponse<ColonyList> findAll(List<String> filter, int page, int size, List<String> sort) 
            throws FiltroException {
        /** 'peticionConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);// En vez de hacer 2 veces el filtrado llamamos al método que lo centraliza
    }

    @SuppressWarnings("null")
    @Override
    public PaginaResponse<ColonyList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            // Configurar ordenamiento
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            // Configurar criterio de filtrado con SpecifiColonyion
            Specification<ColonyDb> filtrosBusquedaSpecifiColonyion = new FiltroBusquedaSpecification<ColonyDb>(
                peticionListadoFiltrado.getListaFiltros());
            // Filtrar y ordenar: puede producir cualquier de los errores controlados en el Colonych
            Page<ColonyDb> page = ColonyRepository.findAll(filtrosBusquedaSpecifiColonyion, pageable);
            //Devolver respuesta
            return ColonyMapper.pageToPaginaResponse(
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
    public ColonyEdit save(ColonyEdit ColonyEdit) {
        if (ColonyEdit == null) {
            throw new IllegalArgumentException("La colonia no puede ser nula");
        }
        ColonyDb ColonyDb = ColonyMapper.INSTANCE.ColonyEditToColonyDb(ColonyEdit);
        ColonyDb savaedColonyDb = ColonyRepository.save(ColonyDb);
        return ColonyMapper.INSTANCE.ColonyDbToColonyEdit(savaedColonyDb);
    }

    @Override
    public void delete(Long id) {
        if (ColonyRepository.existsById(id)) {
            ColonyRepository.deleteById(id);
        }
    }

    @Override
    public ColonyEdit update(Long id, ColonyEdit ColonyEdit) {

        ColonyDb existingEntity = ColonyRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("COLONY_NOT_FOUND_FOR_UPDATE", 
                "No se puede actualizar. La colonia con ID " + id + " no existe"));

        ColonyEdit.setId(id);

        ColonyMapper.INSTANCE.updateColonyDbFromColonyEdit(ColonyEdit, existingEntity);

        ColonyDb updatedEntity = ColonyRepository.save(existingEntity);
        return ColonyMapper.INSTANCE.ColonyDbToColonyEdit(updatedEntity);
    }
    

    @Override
    public ColonyInfo read(Long id) {
        ColonyDb entity = ColonyRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("COLONY_NOT_FOUND", 
                "No se encontró la colonia con ID " + id));
        return ColonyMapper.INSTANCE.ColonyDbToColonyInfo(entity);
    }

}
