package calificaciones.modelo;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String nombre;
    private List<RegistroNota> registros;

    public Curso(){
        this.registros = new ArrayList<>();
    }

    public Curso(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void agregarRegistro(RegistroNota r){
        this.registros.add(r);
    }
    public List<RegistroNota> listarRegistros(){
        return registros;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return '\n' + "{" +
                "id= " + id  +
                ", nombre= " + nombre +
                '}';
    }

}
