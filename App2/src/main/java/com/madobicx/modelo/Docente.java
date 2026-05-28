package com.madobicx.modelo;

public class Docente extends Usuario {

    public Docente(){}
    public Docente(int id, String n, String a, int d){
        super(id, n, a, d);
    }
    @Override
    public int calcularMultaDia(){
        return 5000;
    }
    @Override
    public Rol getRol(){
        return Rol.DOCENTE;
    }

}
