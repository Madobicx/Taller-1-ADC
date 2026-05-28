package com.madobicx;

import com.madobicx.Procesos.*;

public class Main {

    public static void mostrarInformacion(ProcesarDatos a) {
        System.out.println("Resultado: ");
        System.out.println(a.procesar());
        System.out.println();
    }

    public static void main(String[] args) {
        String texto = "Ejercicio #2 Taller 1 - ADC";

        System.out.println("=== ProcesarJSON ===");
        mostrarInformacion(new ProcesarJSON(texto));

        System.out.println("=== ProcesarBase64 ===");
        mostrarInformacion(new ProcesarBase64(texto));

        System.out.println("=== ProcesarXML ===");
        mostrarInformacion(new ProcesarXML(texto));
    }

    }
