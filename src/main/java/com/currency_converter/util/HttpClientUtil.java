package main.java.com.currency_converter.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Clase utilitaria para realizar solicitudes HTTP.
 */
public class HttpClientUtil {

    /**
     * Envía una solicitud HTTP GET al endpoint especificado.
     *
     * @param endpoint El endpoint al que se enviará la solicitud.
     * @return El cuerpo de la respuesta como una cadena.
     * @throws Exception Si ocurre un error durante la solicitud.
     */
    public String sendGetRequest(String endpoint) throws Exception {
        // Crear la URI a partir del endpoint proporcionado
        URI url = URI.create("https://v6.exchangerate-api.com/v6/1c797a96bf5ef048eb3da00f/latest/" + endpoint);

        // Crear un cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Construir la solicitud HTTP GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Devolver el cuerpo de la respuesta
        return response.body();
    }
}
