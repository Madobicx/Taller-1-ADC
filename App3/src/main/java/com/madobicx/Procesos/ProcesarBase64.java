package com.madobicx.Procesos;

import java.util.Base64;

public class ProcesarBase64 implements ProcesarDatos {

    private String texto;

    public ProcesarBase64(String texto) {
        this.texto = texto;
    }

    @Override
    public String procesar() {
        return Base64.getEncoder().encodeToString(texto.getBytes());
    }
}