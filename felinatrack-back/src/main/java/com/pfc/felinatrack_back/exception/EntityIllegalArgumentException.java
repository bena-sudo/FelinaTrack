package com.pfc.felinatrack_back.exception;

import lombok.Getter;

/**
 * Excepción personalizada que se lanza cuando un argumento ilegal es
 * proporcionado al intentar realizar una operación con una entidad.
 * 
 * Esta clase extiende RuntimeException y es utilizada para indicar que uno o
 * más de los argumentos proporcionados a una operación no son válidos o no
 * cumplen con las expectativas de la lógica de negocio.
 * 
 * La excepción incluye un código de error que ayuda a identificar la naturaleza
 * específica del error.
 */
@Getter
public class EntityIllegalArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Número de versión para serialización

    /**
     * Código de error personalizado que identifica el tipo de error.
     * 
     * Este código ayuda a identificar de manera precisa el tipo de argumento
     * inválido o el problema que se está presentando en la operación.
     */
    private final String errorCode;

    /**
     * Constructor para crear una nueva instancia de la excepción
     * EntityIllegalArgumentException.
     * 
     * @param errorCode El código de error que identifica el tipo específico de la
     *                  excepción.
     * @param message   El mensaje que describe el error o la causa del mismo.
     */
    public EntityIllegalArgumentException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
