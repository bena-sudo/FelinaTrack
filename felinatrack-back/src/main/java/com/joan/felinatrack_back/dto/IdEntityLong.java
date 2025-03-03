package com.joan.felinatrack_back.dto;

import com.joan.felinatrack_back.exception.DataValidationException;
import lombok.Value;

/**
 * Clase que representa un ID de entidad de tipo Long.
 * Utiliza un constructor para validar que el ID proporcionado sea un valor
 * numérico válido de tipo Long.
 */
@Value
public class IdEntityLong {

    Long value; // Atributo que almacena el valor del ID de tipo Long

    /**
     * Constructor que recibe un String, intenta convertirlo a Long
     * y lanza una excepción personalizada si el formato es inválido.
     * 
     * @param id El ID en formato String.
     * @throws DataValidationException Si el formato del ID no es válido (no es un
     *                                 número Long).
     */
    public IdEntityLong(String id) {
        try {
            this.value = Long.valueOf(id);
        } catch (NumberFormatException ex) {
            throw new DataValidationException("ID_FORMAT_INVALID",
                    " El ID debe ser un valor numérico de tipo Long (INT64).");
        }
    }
}
