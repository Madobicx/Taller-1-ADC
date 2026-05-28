package com.madobicx.modelo;

public class Empleado extends Usuario {

    public Empleado(){}

    public Empleado(int id, String n, String a, int d)
    {
        super(id, n, a, d);
    }
    @Override
    public int calcularMultaDia(){
        return 2500;
    }

    @Override
    public Rol getRol(){
        return Rol.EMPLEADO;
    }

}
