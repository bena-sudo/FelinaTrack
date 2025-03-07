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
import com.pfc.felinatrack_back.model.db.IssueDb;
import com.pfc.felinatrack_back.model.dto.IssueEdit;
import com.pfc.felinatrack_back.model.dto.IssueInfo;
import com.pfc.felinatrack_back.model.dto.IssueList;
import com.pfc.felinatrack_back.repository.IssueRepository;
import com.pfc.felinatrack_back.src.IssueService;
import com.pfc.felinatrack_back.src.mapper.IssueMapper;


@Service
public class IssueServiceImpl implements IssueService{
    
    @Autowired
    private IssueRepository IssueRepository;

    @Autowired
    private PaginationFactory paginationFactory;

    @Autowired
    private PeticionListadoFiltradoConverter peticionConverter;


    @Override
    public PaginaResponse<IssueList> findAll(List<String> filter, int page, int size, List<String> sort) 
            throws FiltroException {
        /** 'peticionConverter' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        PeticionListadoFiltrado peticion = peticionConverter.convertFromParams(filter, page, size, sort);
        return findAll(peticion);// En vez de hacer 2 veces el filtrado llamamos al método que lo centraliza
    }

    @SuppressWarnings("null")
    @Override
    public PaginaResponse<IssueList> findAll(PeticionListadoFiltrado peticionListadoFiltrado) throws FiltroException {
        /** 'paginationFactory' está en el constructor del service porque utilizando una buena arquitectura
        toda clase externa al Service que contenga un método a ejecutar debería ser testeable de manera
        independiente y para ello debe de estar en el constructor para poderse mockear.**/
        try {
            // Configurar ordenamiento
            Pageable pageable = paginationFactory.createPageable(peticionListadoFiltrado);
            // Configurar criterio de filtrado con SpecifiIssueion
            Specification<IssueDb> filtrosBusquedaSpecifiIssueion = new FiltroBusquedaSpecification<IssueDb>(
                peticionListadoFiltrado.getListaFiltros());
            // Filtrar y ordenar: puede producir cualquier de los errores controlados en el Issuech
            Page<IssueDb> page = IssueRepository.findAll(filtrosBusquedaSpecifiIssueion, pageable);
            //Devolver respuesta
            return IssueMapper.pageToPaginaResponse(
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
    public IssueEdit save(IssueEdit IssueEdit) {
        if (IssueEdit == null) {
            throw new IllegalArgumentException("El asunto no puede ser nulo");
        }
        IssueDb IssueDb = IssueMapper.INSTANCE.IssueEditToIssueDb(IssueEdit);
        IssueDb savaedIssueDb = IssueRepository.save(IssueDb);
        return IssueMapper.INSTANCE.IssueDbToIssueEdit(savaedIssueDb);
    }

    @Override
    public void delete(Long id) {
        if (IssueRepository.existsById(id)) {
            IssueRepository.deleteById(id);
        }
    }

    @Override
    public IssueEdit update(Long id, IssueEdit IssueEdit) {

        IssueDb existingEntity = IssueRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ISSUE_NOT_FOUND_FOR_UPDATE", 
                "No se puede actualizar. El asunto con ID " + id + " no existe"));

        IssueEdit.setId(id);

        IssueMapper.INSTANCE.updateIssueDbFromIssueEdit(IssueEdit, existingEntity);

        IssueDb updatedEntity = IssueRepository.save(existingEntity);
        return IssueMapper.INSTANCE.IssueDbToIssueEdit(updatedEntity);
    }
    

    @Override
    public IssueInfo read(Long id) {
        IssueDb entity = IssueRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("ISSUE_NOT_FOUND", 
                "No se encontró el asunto con ID " + id));
        return IssueMapper.INSTANCE.IssueDbToIssueInfo(entity);
    }
}
