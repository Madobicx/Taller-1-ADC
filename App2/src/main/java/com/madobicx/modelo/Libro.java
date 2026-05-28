package com.madobicx.modelo;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private boolean estado;

    public Libro(){}

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = true;
    }

    public boolean getEstado(){
        return estado;
    }

    public void mPrestado(){
        this.estado = false;
    }
    public void mDisponible(){
        this.estado = true;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }

    @Override
    public String toString(){
        return String.format("| %-15s | %-15s | %-15s | %-15s |",
                isbn,  titulo, autor, estado ? "Disponible" : "Prestado");
    }
}
