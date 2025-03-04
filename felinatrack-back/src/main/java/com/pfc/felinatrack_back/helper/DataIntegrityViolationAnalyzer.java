package com.pfc.felinatrack_back.helper;

import org.springframework.stereotype.Component;

/**
 * Analizador de errores relacionados con violaciones de integridad de datos.
 * <p>
 * Proporciona métodos para interpretar mensajes de error detallados y
 * generar códigos y mensajes de usuario más comprensibles.
 */
@Component
public class DataIntegrityViolationAnalyzer {

    /**
     * Analiza el mensaje detallado de una excepción y determina un código de error
     * estandarizado.
     *
     * @param detailedMessage Mensaje detallado de la excepción.
     * @return Código de error asociado al tipo de violación de integridad
     *         detectada.
     */
    public static String analyzeErrorCode(String detailedMessage) {
        if (detailedMessage != null && detailedMessage.contains("foreign key")) {
            return "VIOLACIÓN DE CLAVE FORÁNEA";
        } else if (detailedMessage != null && detailedMessage.contains("unique constraint")) {
            return "VIOLACIÓN DE RESTRICCIÓN ÚNICA";
        }
        return "VIOLACIÓN DE INTEGRIDAD DE DATOS";
    }

    /**
     * Analiza el mensaje detallado de una excepción y genera un mensaje
     * comprensible para el usuario final.
     *
     * @param detailedMessage Mensaje detallado de la excepción.
     * @return Mensaje amigable para el usuario explicando el tipo de error
     *         ocurrido.
     */
    public static String analyzeUserMessage(String detailedMessage) {
        if (detailedMessage != null && detailedMessage.contains("foreign key")) {
            return "El valor de la clave foránea proporcionado no existe en la tabla relacionada.";
        } else if (detailedMessage != null && detailedMessage.contains("unique constraint")) {
            return "Ya existe un registro con el mismo valor único.";
        }
        return "Se produjo una violación de integridad de datos. Por favor, revisa tu información.";
    }
}
