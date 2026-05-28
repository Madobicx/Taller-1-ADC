package com.madobicx;


import com.madobicx.servicio.Biblioteca;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cargarDatosPredefinidos();

        biblioteca.listaLibros();
        biblioteca.listadoUsuarios();
        biblioteca.listadoLibrosPrestados();
        biblioteca.listadoMultas();

        try{
            biblioteca.exportarLibrosTxt();
            biblioteca.exportarUsuariosTxt();
            biblioteca.exportarMultasTxt();
            biblioteca.exportarReporteCompletoTxt();
            System.out.println("Documentos generados correctamente");
        }catch(IOException e){
            System.out.println("Error generando los archivos: " + e.getMessage());
        }
    }
}