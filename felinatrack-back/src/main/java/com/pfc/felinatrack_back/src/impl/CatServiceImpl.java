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
import com.pfc.felinatrack_back.model.db.CatDb;
import com.pfc.felinatrack_back.model.dto.CatEdit;
import com.pfc.felinatrack_back.model.dto.CatInfo;
import com.pfc.felinatrack_back.model.dto.CatList;
import com.pfc.felinatrack_back.repository.CatRepository;
import com.pfc.felinatrack_back.src.CatService;
import com.pfc.felinatrack_back.src.mapper.CatMapper;


@Service
public class CatServiceImpl implements CatService{
    
    @Autowired
    private CatRepository CatRepository;

    @Autowired
    private PaginationFactory paginationFactory;

    @Autowired
    private PeticionListadoFiltradoConverter peticionConverter;


    @Override
    public PaginaResponse<CatList> findAll(List<String> filter, int page, int size, List<String> sort) 
            throws FiltroException {
        /** 'peticionConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);// En vez de hacer 2 veces el filtrado llamamos al método que lo centraliza
    }

    @SuppressWarnings("null")
    @Override
    public PaginaResponse<CatList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            // Configurar ordenamiento
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            // Configurar criterio de filtrado con Specification
            Specification<CatDb> filtrosBusquedaSpecification = new FiltroBusquedaSpecification<CatDb>(
                peticionListadoFiltrado.getListaFiltros());
            // Filtrar y ordenar: puede producir cualquier de los errores controlados en el catch
            Page<CatDb> page = CatRepository.findAll(filtrosBusquedaSpecification, pageable);
            //Devolver respuesta
            return CatMapper.pageToPaginaResponse(
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
    public CatEdit save(CatEdit CatEdit) {
        if (CatEdit == null) {
            throw new IllegalArgumentException("El gato no puede ser nulo");
        }
        CatDb CatDb = CatMapper.INSTANCE.catEditToCatDb(CatEdit);
        CatDb savaedCatDb = CatRepository.save(CatDb);
        return CatMapper.INSTANCE.catDbToCatEdit(savaedCatDb);
    }

    @Override
    public void delete(Long id) {
        if (CatRepository.existsById(id)) {
            CatRepository.deleteById(id);
        }
    }

    @Override
    public CatEdit update(Long id, CatEdit CatEdit) {

        CatDb existingEntity = CatRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("CAT_NOT_FOUND_FOR_UPDATE", 
                "No se puede actualizar. El gato con ID " + id + " no existe"));

        CatEdit.setId(id);

        CatMapper.INSTANCE.updateCatDbFromCatEdit(CatEdit, existingEntity);

        CatDb updatedEntity = CatRepository.save(existingEntity);
        return CatMapper.INSTANCE.catDbToCatEdit(updatedEntity);
    }
    

    @Override
    public CatInfo read(Long id) {
        CatDb entity = CatRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("CAT_NOT_FOUND", 
                "No se encontró el gato con ID " + id));
        return CatMapper.INSTANCE.catDbToCatInfo(entity);
    }

}
