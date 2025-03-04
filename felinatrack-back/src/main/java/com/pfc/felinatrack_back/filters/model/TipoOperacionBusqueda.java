package com.pfc.felinatrack_back.filters.model;

/**
 * Enum que representa los tipos de operaciones de búsqueda disponibles.
 * Cada tipo de operación corresponde a un símbolo que se utilizará en la
 * consulta de base de datos.
 * 
 * Los tipos de operación disponibles son:
 * - IGUAL: Representado por el símbolo "=".
 * - CONTIENE: Representado por "LIKE", utilizado para realizar búsquedas
 * parciales.
 * - MAYOR_QUE: Representado por el símbolo ">", utilizado para comparar valores
 * mayores.
 * - MENOR_QUE: Representado por el símbolo "<", utilizado para comparar valores
 * menores.
 */
public enum TipoOperacionBusqueda {

    IGUAL("="), // Representa una comparación de igualdad, equivalente a "=".
    CONTIENE("LIKE"), // Representa una búsqueda parcial, equivalente a "LIKE" en SQL.
    MAYOR_QUE(">"), // Representa una comparación de mayor que, equivalente a ">" en SQL.
    MENOR_QUE("<"); // Representa una comparación de menor que, equivalente a "<" en SQL.

    private final String simbolo; // El símbolo asociado a cada operación de búsqueda.

    /**
     * Constructor del enum, que asigna el símbolo correspondiente a la operación.
     * 
     * @param simbolo El símbolo de la operación.
     */
    TipoOperacionBusqueda(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Obtiene el símbolo de la operación de búsqueda.
     * 
     * @return El símbolo de la operación.
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Método estático que permite obtener un valor del enum a partir de un símbolo.
     * 
     * @param simbolo El símbolo de la operación que se quiere convertir en un tipo
     *                de operación.
     * @return El tipo de operación correspondiente al símbolo.
     * @throws IllegalArgumentException Si el símbolo no es válido, se lanza una
     *                                  excepción.
     */
    public static TipoOperacionBusqueda deSimbolo(String simbolo) {
        // Itera sobre los valores del enum y compara el símbolo.
        for (TipoOperacionBusqueda operacion : values()) {
            if (operacion.simbolo.equals(simbolo)) {
                return operacion; // Si encuentra el símbolo, retorna la operación correspondiente.
            }
        }
        // Si no se encuentra una operación con el símbolo, lanza una excepción.
        throw new IllegalArgumentException("Símbolo de operación no válido: " + simbolo);
    }
}
