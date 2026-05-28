package com.madobicx.modelo;

public abstract class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private int documento;

    public Usuario() {}
    public Usuario(int id, String nombre, String apellido, int documento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
    }
    public abstract int calcularMultaDia();

    public abstract Rol getRol();

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDocumento() {
        return documento;
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-15s | %-15s | %-12d |", id, nombre, apellido, documento);
    }
}
