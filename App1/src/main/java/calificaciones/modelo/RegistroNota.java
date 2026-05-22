package calificaciones.modelo;

public class RegistroNota {
    private Nota nota;
    private Estudiante estudiante;

    public RegistroNota(Nota nota, Estudiante estudiante){
        this.nota = nota;
        this.estudiante = estudiante;
    }

    public Nota getNota(){
        return nota;
    }
    public Estudiante getEstudiante(){
        return estudiante;
    }

    @Override
    public String toString() {
        return "RegistroNota{" +
                "nota=" + nota +
                ", estudiante=" + estudiante +
                '}';
    }
}
