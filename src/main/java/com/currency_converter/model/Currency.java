package main.java.com.currency_converter.model;

/**
 * Clase que representa una moneda.
 */
public class Currency {
    // Código de la moneda
    private String code;

    /**
     * Constructor que inicializa el código de la moneda.
     * 
     * @param code Código de la moneda
     */
    public Currency(String code) {
        this.code = code;
    }

    /**
     * Obtiene el código de la moneda.
     * 
     * @return Código de la moneda
     */
    public String getCode() {
        return code;
    }

    /**
     * Establece el código de la moneda.
     * 
     * @param code Código de la moneda
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Devuelve una representación en cadena de la moneda.
     * 
     * @return Representación en cadena de la moneda
     */
    @Override
    public String toString() {
        return "Currency{code='" + code + "'}";
    }
}
