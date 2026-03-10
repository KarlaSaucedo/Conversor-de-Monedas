package com.conversor;
import com.google.gson.Gson;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            Consulta consulta = new Consulta();
            String json = consulta.obtenerDatos();

            Gson gson = new Gson();
            Respuesta datos = gson.fromJson(json, Respuesta.class);

            Map<String, Double> tasas = datos.getConversion_rates();

            Conversor conversor = new Conversor();

            int opcion = 0;

            while (opcion != 9) {

                System.out.println("""
                        -------------------------------
                        CONVERSOR DE MONEDAS
                        -------------------------------
                        1 Dolar(USD) -> Peso (MXN)
                        2 Peso (MXN) -> Dolar(USD)
                        3 Peso (MXN) -> Yuan (CNY)
                        4 Yuan (CNY) -> Peso (MXN)
                        5 Peso (MXN) -> Yen  (JPY)
                        6 Yen  (JPY) -> Peso (MXN)
                        7 Dolar(USD) -> Peso (ARS)
                        8 Peso (ARS) -> Dolar(USD)
                        9 Salir
                        ------------------------------
                        """);

                opcion = scanner.nextInt();

                if (opcion == 9) break;

                System.out.println("Ingrese el monto:");
                double monto = scanner.nextDouble();

                double resultado = 0;
                String monedaOrigen = "";
                String monedaDestino = "";

                switch (opcion) {

                    case 1 -> {
                        resultado = conversor.convertir(monto, tasas.get("MXN"));
                        monedaOrigen = "Dólares";
                        monedaDestino = "Pesos Mexicanos";
                    }

                    case 2 -> {
                        resultado = monto / tasas.get("MXN");
                        monedaOrigen = "Pesos Mexicanos";
                        monedaDestino = "Dólares";
                    }

                    case 3 -> {
                        resultado = monto * (tasas.get("CNY") / tasas.get("MXN"));
                        monedaOrigen = "Pesos Mexicanos";
                        monedaDestino = "Yuanes Chinos";
                    }

                    case 4 -> {
                        resultado = monto * (tasas.get("MXN") / tasas.get("CNY"));
                        monedaOrigen = "Yuanes Chinos";
                        monedaDestino = "Pesos Mexicanos";
                    }

                    case 5 -> {
                        resultado = monto * (tasas.get("JPY") / tasas.get("MXN"));
                        monedaOrigen = "Pesos Mexicanos";
                        monedaDestino = "Yenes Japoneses";
                    }

                    case 6 -> {
                        resultado = monto * (tasas.get("MXN") / tasas.get("JPY"));
                        monedaOrigen = "Yenes Japoneses";
                        monedaDestino = "Pesos Mexicanos";
                    }

                    case 7 -> {
                        resultado = conversor.convertir(monto, tasas.get("ARS"));
                        monedaOrigen = "Dólares";
                        monedaDestino = "Pesos Argentinos";
                    }

                    case 8 -> {
                        resultado = monto / tasas.get("ARS");
                        monedaOrigen = "Pesos Argentinos";
                        monedaDestino = "Dólares";
                    }

                    default -> System.out.println("Opción inválida");
                }

                System.out.println(
                        monto + " " + monedaOrigen +
                                " = " +
                                String.format("%.2f", resultado) +
                                " " + monedaDestino
                );

                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
