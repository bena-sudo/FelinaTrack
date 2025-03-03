package com.joan.felinatrack_back.exception;

import java.util.Map;

import lombok.Getter;

/**
 * Excepción personalizada que se utiliza para manejar errores de validación.
 * 
 * Esta excepción encapsula un código de error y los errores de validación
 * asociados
 * a los campos específicos que han fallado en la validación.
 */
@Getter
public class BindingResultException extends RuntimeException {

    // Código de error asociado a la excepción, proporciona detalles sobre el tipo
    // de error.
    private final String errorCode;

    // Mapa que contiene los errores de validación, donde la clave es el nombre del
    // campo
    // y el valor es el mensaje de error específico para ese campo.
    private final Map<String, String> validationErrors;

    /**
     * Constructor de la clase BindingResultException.
     * 
     * @param errorCode        El código de error que describe el tipo de validación
     *                         fallida.
     * @param validationErrors Mapa que contiene los errores de validación para los
     *                         campos específicos.
     */
    public BindingResultException(String errorCode, Map<String, String> validationErrors) {
        this.errorCode = errorCode;
        this.validationErrors = validationErrors;
    }
}
