package com.madobicx;


import com.madobicx.servicio.Biblioteca;

public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        // Carga los datos predefinidos (libros, usuarios, préstamos)
        biblioteca.cargarDatosPredefinidos();

        // Genera los 4 informes solicitados
        biblioteca.listaLibros();
        biblioteca.listadoUsuarios();
        biblioteca.listadoLibrosPrestados();
        biblioteca.listadoMultas();
    }
}