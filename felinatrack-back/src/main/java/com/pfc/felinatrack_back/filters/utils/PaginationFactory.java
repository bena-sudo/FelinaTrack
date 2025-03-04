package com.pfc.felinatrack_back.filters.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.pfc.felinatrack_back.filters.model.PeticionListadoFiltrado;

/**
 * Fábrica para la creación de objetos {@link Pageable} a partir de los
 * parámetros de una petición filtrada.
 */
@Component
public class PaginationFactory {

    /**
     * Genera un {@link Pageable} basado en la información de la petición.
     *
     * @param peticion Objeto que contiene los parámetros de paginación y
     *                 ordenación.
     * @return Objeto {@link Pageable} configurado con la página, tamaño y orden
     *         correspondientes.
     */
    public Pageable createPageable(PeticionListadoFiltrado peticion) {
        return PageRequest.of(
                peticion.getPage(),
                peticion.getSize(),
                createSort(peticion.getSort()));
    }

    /**
     * Crea un {@link Sort} a partir de una lista de criterios de ordenación.
     * Cada criterio debe tener el formato "campo,direccion" donde la dirección
     * puede ser ASC o DESC.
     *
     * @param sortCriteria Lista de criterios de ordenación.
     * @return Objeto {@link Sort} configurado o sin orden si la lista está vacía o
     *         es nula.
     */
    private Sort createSort(List<String> sortCriteria) {
        if (sortCriteria == null || sortCriteria.isEmpty()) {
            return Sort.unsorted();
        }

        List<Order> orders = sortCriteria.stream()
                .map(this::createOrder)
                .collect(Collectors.toList());

        return Sort.by(orders);
    }

    /**
     * Crea una orden de ordenación ({@link Order}) a partir de un criterio
     * individual.
     *
     * @param criterion Cadena con el formato "campo,direccion". Si no se especifica
     *                  dirección, se asume ASC.
     * @return Objeto {@link Order} con el campo y dirección especificados.
     */
    private Order createOrder(String criterion) {
        String[] parts = criterion.split(",");
        // Si no se especifica dirección, se utiliza ascendente por defecto.
        Direction direction = parts.length > 1
                ? Direction.fromString(parts[1])
                : Direction.ASC;
        return new Order(direction, parts[0]);
    }
}
