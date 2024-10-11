package main.java.com.currency_converter.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import main.java.com.currency_converter.model.Currency;
import main.java.com.currency_converter.model.ExchangeRate;
import main.java.com.currency_converter.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateService {
    private HttpClientUtil httpClientUtil;
    private Gson gson;

    // Constructor de la clase ExchangeRateService
    public ExchangeRateService() {
        this.httpClientUtil = new HttpClientUtil();
        this.gson = new Gson();
    }

    /**
     * Obtiene la tasa de cambio entre dos monedas.
     *
     * @param baseCode Código de la moneda base.
     * @param targetCode Código de la moneda objetivo.
     * @return Un objeto ExchangeRate que contiene la tasa de cambio y otra información relevante.
     * @throws Exception Si ocurre un error durante la solicitud HTTP o el procesamiento de la respuesta.
     */
    public ExchangeRate getExchangeRate(String baseCode, String targetCode) throws Exception {
        String endpoint = "pair/" + baseCode + "/" + targetCode;
        String jsonResponse = httpClientUtil.sendGetRequest(endpoint);

        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        double conversionRate = jsonObject.get("conversion_rate").getAsDouble();
        String lastUpdated = jsonObject.get("time_last_update_utc").getAsString();
        String nextUpdate = jsonObject.get("time_next_update_utc").getAsString();

        Currency baseCurrency = new Currency(baseCode);
        Currency targetCurrency = new Currency(targetCode);

        return new ExchangeRate(baseCurrency, targetCurrency, conversionRate, lastUpdated, nextUpdate);
    }

    /**
     * Obtiene un mapa de las monedas disponibles y sus nombres.
     *
     * @return Un mapa donde las claves son los códigos de las monedas y los valores son los nombres de las monedas.
     */
    public Map<String, String> getAvailableCurrencies() {
        Map<String, String> currencies = new HashMap<>();

        currencies.put("USD", "United States Dollar");
        currencies.put("EUR", "Euro");
        currencies.put("ARS", "Argentine Peso");
        currencies.put("BOB", "Bolivian Boliviano");
        currencies.put("BRL", "Brazilian Real");
        currencies.put("CLP", "Chilean Peso");
        currencies.put("COP", "Colombian Peso");
        currencies.put("CRC", "Costa Rican Colón");
        currencies.put("CUP", "Cuban Peso");
        currencies.put("DOP", "Dominican Peso");
        currencies.put("GTQ", "Guatemalan Quetzal");
        currencies.put("HNL", "Honduran Lempira");
        currencies.put("MXN", "Mexican Peso");
        currencies.put("NIO", "Nicaraguan Córdoba");
        currencies.put("PAB", "Panamanian Balboa");
        currencies.put("PYG", "Paraguayan Guarani");
        currencies.put("PEN", "Peruvian Sol");
        currencies.put("UYU", "Uruguayan Peso");
        currencies.put("VES", "Venezuelan Bolívar");
        // Continúa con más monedas si es necesario

        return currencies;
    }
}
