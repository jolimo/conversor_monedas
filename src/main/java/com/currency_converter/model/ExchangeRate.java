package main.java.com.currency_converter.model;

/**
 * Clase que representa una tasa de cambio entre dos monedas.
 */
public class ExchangeRate {
    private Currency baseCurrency; // Moneda base
    private Currency targetCurrency; // Moneda objetivo
    private double rate; // Tasa de cambio
    private String lastUpdated; // Fecha de la última actualización
    private String nextUpdate; // Fecha de la próxima actualización

    /**
     * Constructor para inicializar una instancia de ExchangeRate.
     *
     * @param baseCurrency Moneda base
     * @param targetCurrency Moneda objetivo
     * @param rate Tasa de cambio
     * @param lastUpdated Fecha de la última actualización
     * @param nextUpdate Fecha de la próxima actualización
     */
    public ExchangeRate(Currency baseCurrency,
                        Currency targetCurrency,
                        double rate,
                        String lastUpdated,
                        String nextUpdate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
        this.nextUpdate = nextUpdate;
    }

    /**
     * Obtiene la moneda base.
     *
     * @return Moneda base
     */
    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Obtiene la moneda objetivo.
     *
     * @return Moneda objetivo
     */
    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * Obtiene la tasa de cambio.
     *
     * @return Tasa de cambio
     */
    public double getRate() {
        return rate;
    }

    /**
     * Obtiene la fecha de la última actualización.
     *
     * @return Fecha de la última actualización
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Obtiene la fecha de la próxima actualización.
     *
     * @return Fecha de la próxima actualización
     */
    public String getNextUpdate() {
        return nextUpdate;
    }

    /**
     * Devuelve una representación en cadena de la instancia de ExchangeRate.
     *
     * @return Representación en cadena de la instancia
     */
    @Override
    public String toString() {
        return "ExchangeRate{" +
                "baseCurrency=" + baseCurrency +
                ", targetCurrency=" + targetCurrency +
                ", rate=" + rate +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", nextUpdate='" + nextUpdate + '\'' +
                '}';
    }
}
