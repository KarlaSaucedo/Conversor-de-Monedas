package com.conversor;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta {
    public String obtenerDatos() throws IOException, InterruptedException {

        String direccion = "https://v6.exchangerate-api.com/v6/db1da9348e27cd32ba705bb2/latest/USD";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }
}
