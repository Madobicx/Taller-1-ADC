package com.madobicx.modelo;

public class Multa {
    private int id;
    private Prestamo prestamo;
    private int Retraso;
    private int valorDia;
    private int total;

    public Multa(){}
    public Multa(int id, Prestamo prestamo, int Retraso, int valorDia){
        this.id = id;
        this.prestamo = prestamo;
        this.Retraso = Retraso;
        this.valorDia = valorDia;
        this.total = Retraso *  valorDia;
    }

    public int getTotal(){
        return total;
    }

    public int getId(){
        return id;
    }

    public int getRetraso(){
        return Retraso;
    }

    public Prestamo getPrestamo(){
        return prestamo;
    }

    public Usuario getUsuario(){
        return prestamo.getUsuario();
    }

    public Libro getLibro(){
        return prestamo.getLibro();
    }

    @Override
    public String toString() {
        return String.format("| %-15d | %-15s | %-15s | %-15d | %-15d | %-15d |",
                id,
                prestamo.getLibro().getTitulo(),
                prestamo.getUsuario().getNombre(),
                Retraso,
                valorDia,
                total);
    }
}
