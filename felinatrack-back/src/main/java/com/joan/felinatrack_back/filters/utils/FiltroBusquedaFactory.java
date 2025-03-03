package com.joan.felinatrack_back.filters.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.joan.felinatrack_back.exception.FiltroException;
import com.joan.felinatrack_back.filters.model.FiltroBusqueda;
import com.joan.felinatrack_back.filters.model.TipoOperacionBusqueda;

/**
 * Fábrica para la creación de listas de filtros de búsqueda a partir de cadenas
 * con formato específico.
 */
@Component
public class FiltroBusquedaFactory {

    /** Separador utilizado para dividir los elementos del filtro. */
    private final static CharSequence separador = ":";

    /**
     * Crea una lista de {@link FiltroBusqueda} a partir de una lista de cadenas con
     * formato:
     * 
     * <pre>
     * ATRIBUTO:OPERACION:VALOR
     * </pre>
     *
     * @param filtros Lista de filtros como cadenas de texto.
     * @return Lista de objetos {@link FiltroBusqueda}.
     * @throws FiltroException Si alguno de los filtros no tiene el formato esperado
     *                         o contiene una operación inválida.
     */
    public List<FiltroBusqueda> crearListaFiltrosBusqueda(List<String> filtros) throws FiltroException {
        if (filtros == null || filtros.isEmpty()) {
            return Collections.emptyList();
        }

        try {
            return filtros.stream()
                    .map(FiltroBusquedaFactory::createFiltro)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new FiltroException(
                    "BAD_FILTER",
                    "Error: Filtro incorrecto",
                    e.getMessage());
        }

    }

    /**
     * Crea un {@link FiltroBusqueda} a partir de una cadena con formato:
     * 
     * <pre>
     * ATRIBUTO:OPERACION:VALOR
     * </pre>
     *
     * @param filtro Cadena que representa el filtro.
     * @return Objeto {@link FiltroBusqueda} generado.
     * @throws IllegalArgumentException Si el filtro no tiene el formato esperado o
     *                                  la operación no es válida.
     */
    private static FiltroBusqueda createFiltro(String filtro) {
        if (filtro == null || !filtro.contains(separador)) {
            throw new IllegalArgumentException("El filtro proporcionado no tiene el formato esperado" +
                    " (ATRIBUTO" + separador.toString() + "OPERACION" + separador.toString() + "VALOR).");
        }

        // Dividimos el filtro en máximo tres partes: atributo, operación y valor
        String[] partes = filtro.split(separador.toString(), 3);
        if (partes.length < 3) {
            throw new IllegalArgumentException("El filtro proporcionado no tiene el formato esperado" +
                    " (ATRIBUTO" + separador.toString() + "OPERACION" + separador.toString() + "VALOR).");
        }

        String atributo = partes[0].trim();
        String operacionTexto = partes[1].trim();

        // En caso de que el valor contenga el separador, se conserva como parte del
        // valor completo
        String valor = Arrays.stream(partes, 2, partes.length)
                .reduce((a, b) -> a + separador.toString() + b)
                .orElse("").trim();

        TipoOperacionBusqueda operacion;
        try {
            operacion = TipoOperacionBusqueda.valueOf(operacionTexto.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Operación no válida: " + operacionTexto);
        }

        return new FiltroBusqueda(atributo, operacion, valor);
    }
}
