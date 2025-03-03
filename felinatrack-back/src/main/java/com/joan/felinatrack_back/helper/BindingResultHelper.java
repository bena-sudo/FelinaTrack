package com.joan.felinatrack_back.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

import com.joan.felinatrack_back.exception.BindingResultException;

/**
 * Clase de utilidad para manejar y validar errores de {@link BindingResult}.
 * <p>
 * No se puede instanciar.
 */
public class BindingResultHelper {

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private BindingResultHelper() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }

    /**
     * Extrae los errores de validación de un {@link BindingResult} y los devuelve
     * como un mapa.
     *
     * @param bindingResult Resultado de la validación.
     * @return Mapa con los campos que presentan errores y sus respectivos mensajes.
     */
    public static Map<String, String> extractErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    /**
     * Valida si existen errores en el {@link BindingResult} y lanza una excepción
     * {@link BindingResultException} si los hay.
     *
     * @param bindingResult Resultado de la validación.
     * @param errorCode     Código de error personalizado para identificar el
     *                      contexto del fallo.
     * @throws BindingResultException Si existen errores de validación.
     */
    public static void validateBindingResult(BindingResult bindingResult, String errorCode) {
        if (bindingResult.hasErrors()) {
            throw new BindingResultException(errorCode, extractErrors(bindingResult));
        }
    }
}
