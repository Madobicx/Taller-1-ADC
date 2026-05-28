package com.madobicx.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private int id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fPrestamo;
    private LocalDate fEntrega;
    private LocalDate fDevuelto;

    public Prestamo(int id, Libro libro, Usuario usuario, LocalDate fPrestamo, LocalDate fEntrega) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fPrestamo = fPrestamo;
        this.fEntrega = fEntrega;
        this.fDevuelto = null;
    }

    public void rDevolucion(LocalDate fecha){
        this.fDevuelto = fecha;
    }

    public boolean eDevuelto(){
        return fDevuelto != null;
    }

    public boolean eRetraso(){
        return eDevuelto() && fDevuelto.isAfter(fEntrega);
    }

    public int dRetraso(){
        if(!eRetraso()){return 0;}

        return (int) ChronoUnit.DAYS.between(fEntrega, fDevuelto);
    }

    public Multa gMulta(int idMulta){
        if(!eRetraso()){return null;}
        return new Multa(idMulta, this, dRetraso(), usuario.calcularMultaDia());
    }

    public int getId() {
        return id;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getfPrestamo() {
        return fPrestamo;
    }

    public LocalDate getfEntrega() {
        return fEntrega;
    }

    public LocalDate getfDevuelto() {
        return fDevuelto;
    }
}
