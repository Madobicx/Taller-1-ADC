package com.madobicx.Procesos;

public class ProcesarJSON implements ProcesarDatos {

    private String texto;

    public ProcesarJSON(String texto) {
        this.texto = texto;
    }

    @Override
    public String procesar() {
        return "{ \"datos\": \"" + texto + "\", \"longitud\": " + texto.length() + " }";
    }
}