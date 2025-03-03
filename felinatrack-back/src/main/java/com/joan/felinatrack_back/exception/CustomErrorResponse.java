package com.joan.felinatrack_back.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * Clase que representa la respuesta personalizada para los errores.
 * 
 * Esta clase contiene información relevante sobre el error ocurrido, como el
 * código del error, el mensaje principal, un mensaje detallado (opcional) y la
 * marca de tiempo (timestamp) cuando ocurrió el error.
 * 
 * Se utiliza para proporcionar detalles sobre un error a los consumidores de la
 * API.
 */
@Getter
public class CustomErrorResponse {

    /**
     * Código del error, representa el tipo o identificador del error.
     */
    @Schema(example = "ERRROR_CODE_STRING", description = "Código del error (String)")
    private final String errorCode;

    /**
     * Mensaje principal del error, que proporciona una breve descripción del mismo.
     */
    @Schema(example = "Mensaje", description = "Mensaje informativo del error")
    private final String message;

    /**
     * Mensaje detallado del error, opcional. Proporciona más contexto sobre el
     * error. Este campo puede estar nulo si no se proporciona información
     * adicional.
     */
    @Schema(example = "Mensaje detallado del error", description = "Mensaje más detallado del error que puede estar nulo.")
    private String detailedMessage;

    /**
     * Marca de tiempo (timestamp) de cuando ocurrió el error. Utiliza el formato
     * ISO estándar.
     */
    @Schema(example = "2025-01-31T12:59:10.123456789", description = "Timestamp de cuando sucedió el error")
    private final String timestamp;

    /**
     * Constructor principal para crear una respuesta de error con el código y el
     * mensaje. El campo 'detailedMessage' se deja vacío en este caso.
     *
     * @param errorCode Código de error que identifica el tipo de error.
     * @param message   Mensaje breve que describe el error.
     */
    public CustomErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME); // Se asigna la fecha y hora
                                                                                      // actual
    }

    /**
     * Constructor que permite incluir un mensaje detallado sobre el error.
     * 
     * @param errorCode       Código de error que identifica el tipo de error.
     * @param message         Mensaje breve que describe el error.
     * @param detailedMessage Mensaje detallado del error, puede ser nulo.
     */
    public CustomErrorResponse(String errorCode, String message, String detailedMessage) {
        this.errorCode = errorCode;
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME); // Se asigna la fecha y hora
                                                                                      // actual
    }

    /**
     * Método setter para el mensaje detallado. Permite modificar el mensaje
     * detallado después de la creación del objeto.
     * 
     * @param detailedMessage El mensaje detallado sobre el error.
     */
    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }
}
