package com.madobicx.Procesos;

public class ProcesarXML implements ProcesarDatos {

    private String texto;

    public ProcesarXML(String texto) {
        this.texto = texto;
    }

    @Override
    public String procesar() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<datos>\n" +
                "    <texto>" + texto + "</texto>\n" +
                "    <longitud>" + texto.length() + "</longitud>\n" +
                "</datos>";
    }
}