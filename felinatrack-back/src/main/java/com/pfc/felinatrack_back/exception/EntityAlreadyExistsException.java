package com.pfc.felinatrack_back.exception;

import lombok.Getter;

/**
 * Excepción personalizada que se lanza cuando una entidad ya existe en el
 * sistema.
 * 
 * Esta clase extiende RuntimeException y es utilizada para indicar que la
 * entidad que se intenta crear
 * o insertar ya existe en la base de datos o en la lógica del sistema,
 * impidiendo la operación.
 * 
 * La excepción incluye un código de error específico para ayudar a identificar
 * la causa precisa del problema.
 */
@Getter
public class EntityAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Número de versión para serialización

    /**
     * Código de error personalizado que identifica el tipo de error.
     * 
     * El código de error ayuda a identificar de manera precisa el problema que ha
     * ocurrido, facilitando el diagnóstico o la respuesta al usuario.
     */
    private final String errorCode;

    /**
     * Constructor para crear una nueva instancia de la excepción
     * EntityAlreadyExistsException.
     * 
     * @param errorCode El código de error que identifica el tipo específico de la
     *                  excepción.
     * @param message   El mensaje que describe el error o la causa del mismo.
     */
    public EntityAlreadyExistsException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
