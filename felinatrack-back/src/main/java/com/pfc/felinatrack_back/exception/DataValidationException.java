package com.pfc.felinatrack_back.exception;

import lombok.Getter;

/**
 * Excepción personalizada para errores de validación de datos.
 * 
 * Esta clase extiende RuntimeException y permite manejar de manera específica
 * los errores que ocurren cuando los datos proporcionados no cumplen con las
 * reglas de validación.
 * 
 * La excepción incluye un código de error que puede ser utilizado para
 * identificar el tipo específico de error.
 */
@Getter
public class DataValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Número de versión para serialización

    /**
     * Código de error específico que identifica el tipo de error de validación.
     * 
     * Este código puede ser utilizado para proporcionar detalles sobre el tipo de
     * error que ocurrió, facilitando así su manejo y la retroalimentación al
     * usuario.
     */
    private final String errorCode;

    /**
     * Constructor que crea una nueva instancia de DataValidationException con el
     * código de error y el mensaje.
     * 
     * @param errorCode El código que identifica el tipo de error.
     * @param message   El mensaje que proporciona más detalles sobre el error.
     */
    public DataValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
