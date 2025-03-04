package com.pfc.felinatrack_back.exception;

import lombok.Getter;

/**
 * Excepción personalizada que se lanza cuando no se encuentra una entidad en la
 * base de datos o en algún otro repositorio de datos, indicando que la entidad
 * solicitada no existe.
 * 
 * Esta clase extiende RuntimeException y se utiliza para señalar que se ha
 * intentado realizar una operación sobre una entidad que no existe, como por
 * ejemplo, intentar obtener un registro que no está presente en la base de
 * datos.
 */
@Getter
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Número de versión para serialización

    /**
     * Código de error personalizado que identifica el tipo de error.
     * 
     * Este código ayuda a identificar el tipo de entidad que no fue encontrada o
     * puede usarse para proporcionar un diagnóstico más detallado del error.
     */
    private final String errorCode;

    /**
     * Constructor para crear una nueva instancia de la excepción
     * EntityNotFoundException.
     * 
     * @param errorCode El código de error que identifica el tipo de la excepción
     *                  (por ejemplo, "ENTITY_NOT_FOUND").
     * @param message   El mensaje que describe el error o la causa del mismo (por
     *                  ejemplo, "La entidad no fue encontrada").
     */
    public EntityNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
