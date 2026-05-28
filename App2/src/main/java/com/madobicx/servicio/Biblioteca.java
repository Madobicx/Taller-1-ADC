package com.madobicx.servicio;

import com.madobicx.modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Biblioteca {

    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    private List<Multa> multas;

    private int gIdPrestamo = 2600;
    private int gIdMulta = 12600;


    public Biblioteca(){
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.multas = new ArrayList<>();
    }

    public void cargarDatosPredefinidos() {
        libros.add(new Libro("978-0156012195", "El Principito", "Saint-Exupéry"));
        libros.add(new Libro("978-0307474728", "Cien años de soledad", "García Márquez"));
        libros.add(new Libro("978-8424116545", "Don Quijote", "Cervantes"));
        libros.add(new Libro("978-0307743657", "1984", "Orwell"));
        libros.add(new Libro("978-8497592208", "La Odisea", "Homero"));

        usuarios.add(new Docente(1, "Carlos", "Méndez", 11223344));
        usuarios.add(new Estudiante(2, "Ana", "Rodríguez", 1006789432));
        usuarios.add(new Empleado(3, "Luisa", "Pérez", 55667788));
        usuarios.add(new Estudiante(4, "Diego", "Castro", 1009876543));
        usuarios.add(new Docente(5, "Marta", "Sánchez", 22334455));


        Prestamo p1 = prestamoLibro(usuarios.get(1), libros.get(0),
                LocalDate.of(2026, 5, 10), LocalDate.of(2026, 5, 17));
        devolverLibro(p1, LocalDate.of(2026, 5, 22));

        Prestamo p2 = prestamoLibro(usuarios.get(0), libros.get(1),
                LocalDate.of(2026, 5, 5), LocalDate.of(2026, 5, 15));
        devolverLibro(p2, LocalDate.of(2026, 5, 18));

        Prestamo p3 = prestamoLibro(usuarios.get(2), libros.get(2),
                LocalDate.of(2026, 5, 8), LocalDate.of(2026, 5, 20));
        devolverLibro(p3, LocalDate.of(2026, 5, 19));

        Prestamo p4 = prestamoLibro(usuarios.get(3), libros.get(3),
                LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 10));
        devolverLibro(p4, LocalDate.of(2026, 5, 20));

        prestamoLibro(usuarios.get(4), libros.get(4),
                LocalDate.of(2026, 5, 15), LocalDate.of(2026, 5, 25));
    }


    public Prestamo prestamoLibro(Usuario user, Libro lib, LocalDate fPretamo, LocalDate fEntrega){
        if(!lib.getEstado()){
            System.out.println("Libro " + lib.getTitulo() + " no esta disponible");
            return null;
        }
        lib.mPrestado();
        Prestamo p = new Prestamo(gIdPrestamo++, lib,  user, fPretamo, fEntrega);
        prestamos.add(p);
        return p;
    }
    public Multa devolverLibro(Prestamo p, LocalDate fDevuelto){
        p.rDevolucion(fDevuelto);
        p.getLibro().mDisponible();
        Multa multa = p.gMulta(gIdMulta);
        if(multa != null){
            multas.add(multa);
            gIdMulta++;
        }
        return multa;
    }
    public void listaLibros(){
        listaLibros(System.out);
    }

    public void listaLibros(PrintStream out){
        out.println("Libros: ");
        out.printf("| %-15s | %-15s | %-15s | %-15s | %n", "ISBN", "TITULO", "AUTOR", "ESTADO");
        for(Libro l : libros){
            out.println(l.toString());
        }
    }

    public void listadoLibrosPrestados(){
        listadoLibrosPrestados(System.out);
    }

    public void listadoLibrosPrestados(PrintStream out){
        out.println("LibrosPrestados: ");
        out.printf("| %-15s | %-15s | %-15s | %-15s | %n", "ISBN", "TITULO", "AUTOR", "ESTADO");
        int auxLibros = 0;
        List<Libro> auxLib = new ArrayList<>();
        for(Libro l : libros){
            if(!l.getEstado()){
                auxLibros++;
                auxLib.add(l);
            }
        }
        if(auxLibros == libros.size()){
            out.println("No hay prestamos activos");
            return;
        }
        for(Libro l : auxLib){
            out.println(l.toString());
        }
    }

    public void listadoUsuarios(){
        listadoUsuarios(System.out);
    }

    public void listadoUsuarios(PrintStream out){
        out.println("Usuarios: ");
        out.printf("| %-5s | %-15s | %-15s | %-15s | %-15s | %n", "ID", "NOMBRE", "APELLIDO", "DOCUMENTO", "ROL");
        for(Usuario u : usuarios){
            out.println(u.toString() + u.getRol() + " |");
        }
    }

    public void listadoMultas(){
        listadoMultas(System.out);
    }

    public void listadoMultas(PrintStream out){
        out.println("Multas: ");
        out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %n", "ID", "LIBRO PRESTADO", "USUARIO", "RETRASO", "VALORDIA", "TOTAL");
        if(multas.isEmpty()){
            out.println("No hay multas registradas");
            return;
        }
        for(Multa m : multas){
            out.println(m.toString());
        }
    }

    public void exportarLibrosTxt() throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("libros.txt"))) {
            listaLibros(ps);
        }
    }

    public void exportarUsuariosTxt() throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("usuarios.txt"))) {
            listadoUsuarios(ps);
        }
    }

    public void exportarMultasTxt() throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("multas.txt"))) {
            listadoMultas(ps);
        }
    }

    public void exportarReporteCompletoTxt() throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("biblioteca.txt"))) {
            ps.println(" REPORTE BIBLIOTECA ");
            ps.println();
            listaLibros(ps);
            ps.println();
            listadoLibrosPrestados(ps);
            ps.println();
            listadoUsuarios(ps);
            ps.println();
            listadoMultas(ps);
        }
    }

}
