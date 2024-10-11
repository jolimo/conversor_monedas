package main.java.com.currency_converter.controller;

import main.java.com.currency_converter.model.ExchangeRate;
import main.java.com.currency_converter.service.ExchangeRateService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Controlador para manejar las operaciones de conversión de moneda.
 */
public class ExchangeRateController {
    private ExchangeRateService exchangeRateService;
    private List<String> conversionHistory;

    /**
     * Constructor que inicializa el servicio de tasa de cambio y el historial de conversiones.
     */
    public ExchangeRateController() {
        this.exchangeRateService = new ExchangeRateService();
        this.conversionHistory = new ArrayList<>();
    }

    /**
     * Método principal que ejecuta el menú de opciones para el usuario.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            showMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    performConversion(scanner);
                    break;
                case "2":
                    showConversionHistory();
                    break;
                case "3":
                    showAvailableCurrencies();
                    break;
                case "4":
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción correcta.");
            }
        } while (!option.equals("4"));

        scanner.close();
    }

    /**
     * Muestra el menú de opciones al usuario.
     */
    private void showMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Convertir moneda");
        System.out.println("2. Ver historial de conversiones");
        System.out.println("3. Ver monedas disponibles");
        System.out.println("4. Salir");
    }

    /**
     * Realiza la conversión de moneda solicitada por el usuario.
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    private void performConversion(Scanner scanner) {
        System.out.print("Ingrese el código de la moneda base (ej. USD): ");
        String baseCode = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese el código de la moneda destino (ej. EUR): ");
        String targetCode = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Cantidad no válida. Ingrese un número.");
            }
        }

        try {
            ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(baseCode, targetCode);
            double conversionRate = exchangeRate.getRate();
            double convertedAmount = convertCurrency(amount, conversionRate);
            System.out.println("Tasa de cambio: " + conversionRate);
            System.out.println("Cantidad convertida: " + convertedAmount);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            String historyEntry = formattedDateTime +
                    " - " + baseCode + " a " + targetCode +
                    ": " + amount + " = " + convertedAmount;

            conversionHistory.add(historyEntry);

        } catch (Exception e) {
            System.err.println("Error al obtener la tasa de cambio: " + e.getMessage());
        }
    }

    /**
     * Muestra el historial de conversiones realizadas.
     */
    private void showConversionHistory() {
        System.out.println("\n=== Historial de Conversiones ===");
        if (conversionHistory.isEmpty()) {
            System.out.println("No se han realizado conversiones.");
        } else {
            for (String record : conversionHistory) {
                System.out.println(record);
            }
        }
    }

    /**
     * Muestra las monedas disponibles para la conversión.
     */
    private void showAvailableCurrencies() {
        System.out.println("\n=== Monedas Disponibles ===");

        Map<String, String> availableCurrencies = exchangeRateService.getAvailableCurrencies();

        if (availableCurrencies.isEmpty()) {
            System.out.println("No se pudo cargar la lista de monedas.");
        } else {
            for (Map.Entry<String, String> entry : availableCurrencies.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    private double convertCurrency(double amount, double conversionRate) {
        return amount * conversionRate;
    }

    public static void main(String[] args) {
        ExchangeRateController controller = new ExchangeRateController();
        controller.run();
    }
}
