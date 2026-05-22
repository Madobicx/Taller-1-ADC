package calificaciones.modelo;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String documento;
    private boolean status;

    public Estudiante(){
        this.status = true;
    }

    public Estudiante(int id, String nombre, String apellido, String documento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getDocumento() {return documento;}
    public boolean getStatus() {return status;}
    public void setStatus(boolean status){
        this.status=status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return  '\n' + "{" +
                "id=" + id +
                ", nombre= " + nombre +
                ", apellido= " + apellido +
                ", documento= " + documento +
                ", status=" + status +
                '}';
    }
}
