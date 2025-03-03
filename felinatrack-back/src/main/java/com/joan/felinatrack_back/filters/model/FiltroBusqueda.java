package com.joan.felinatrack_back.filters.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un filtro de búsqueda utilizado para realizar consultas dinámicas.
 * 
 * Esta clase contiene un atributo (campo), una operación de búsqueda y un valor
 * que se utilizarán para construir una consulta de filtrado.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FiltroBusqueda {

    /**
     * El atributo o campo de la entidad sobre el cual se realizará el filtro.
     * Este campo debe tener al menos 1 carácter de longitud.
     */
    @Size(min = 1, message = "Debe especificar un atributo")
    private String atributo;

    /**
     * La operación que se va a realizar sobre el atributo, como "igual",
     * "contiene", etc.
     * Este campo debe tener al menos 1 carácter de longitud.
     */
    @Size(min = 1, message = "Debe especificar una operación")
    private TipoOperacionBusqueda operacion;

    /**
     * El valor con el que se va a comparar el atributo.
     * No puede ser nulo.
     */
    @NotNull(message = "El valor no puede estar vacio")
    private Object valor;
}
