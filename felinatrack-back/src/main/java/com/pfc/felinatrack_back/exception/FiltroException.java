package com.pfc.felinatrack_back.exception;

import lombok.Getter;

/**
 * Excepción personalizada que se utiliza para manejar errores relacionados con
 * los filtros de búsqueda, validación de datos u otros problemas asociados a la
 * lógica de filtros en la aplicación.
 * 
 * Esta excepción puede ser lanzada cuando los filtros de búsqueda no cumplen
 * con los requisitos esperados, contienen valores incorrectos o generan errores
 * al intentar usarlos en las operaciones de búsqueda.
 */
@Getter
public class FiltroException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Número de versión para serialización

    /**
     * Código de error personalizado que identifica el tipo específico de error
     * relacionado con el filtro.
     * 
     * Este código puede ser útil para distinguir entre diferentes tipos de errores
     * de filtro, como "FILTER_INVALID" o "FILTER_OUT_OF_RANGE".
     */
    private final String errorCode;

    /**
     * Mensaje de error principal que describe el problema con el filtro de forma
     * concisa.
     * 
     * Este mensaje es generalmente un texto breve que explica cuál es el problema
     * general, como "El valor del filtro no es válido".
     */
    private final String message;

    /**
     * Mensaje detallado que proporciona información adicional sobre el error.
     * 
     * Este mensaje es opcional, pero puede incluir detalles específicos como el
     * tipo de filtro que falló o el valor específico que causó el problema.
     */
    private final String detailedMessage;

    /**
     * Constructor para crear una nueva instancia de FiltroException.
     * 
     * @param errorCode       El código de error que identifica el tipo de la
     *                        excepción (por ejemplo, "FILTER_INVALID").
     * @param message         El mensaje de error que describe el problema de manera
     *                        general.
     * @param detailedMessage Un mensaje más detallado que proporciona más contexto
     *                        sobre el error.
     */
    public FiltroException(String errorCode, String message, String detailedMessage) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.detailedMessage = detailedMessage;
    }
}
