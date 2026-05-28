package com.madobicx.modelo;

public class Estudiante extends Usuario {

    public Estudiante(){}

    public Estudiante(int id, String n, String a, int d){
        super(id, n, a, d);
    }
    @Override
    public int calcularMultaDia(){
        return 1000;
    }
    @Override
    public Rol getRol(){
        return Rol.ESTUDIANTE;
    }

}
