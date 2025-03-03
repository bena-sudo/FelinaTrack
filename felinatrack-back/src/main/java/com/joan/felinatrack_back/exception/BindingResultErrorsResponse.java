package com.joan.felinatrack_back.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import lombok.Data;

/**
 * Esta clase se utiliza para crear una respuesta estructurada que contiene
 * los detalles de los errores de validación de los resultados de enlace
 * (BindingResult). Incluye el código de error, los errores de validación y una
 * marca de tiempo.
 */
@Data
public class BindingResultErrorsResponse {

    // Código de error relacionado con la validación.
    private String errorCode;

    // Un mapa que contiene los errores de validación, donde la clave es el nombre
    // del campo y el valor es el mensaje de error correspondiente.
    private Map<String, String> validationErrors;

    // Marca de tiempo en formato ISO que indica cuándo se generó la respuesta de
    // error.
    private String timestamp;

    /**
     * Constructor de la clase BindingResultErrorsResponse.
     * 
     * @param errorCode        Código de error que describe el tipo de error.
     * @param validationErrors Errores de validación con los campos y sus mensajes
     *                         respectivos. La marca de tiempo se genera
     *                         automáticamente al crear la respuesta.
     */
    public BindingResultErrorsResponse(String errorCode, Map<String, String> validationErrors) {
        this.errorCode = errorCode;
        this.validationErrors = validationErrors;
        // La marca de tiempo se establece como la fecha y hora actual en formato ISO.
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
