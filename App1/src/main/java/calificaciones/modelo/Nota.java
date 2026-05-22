package calificaciones.modelo;

public abstract class Nota {

    protected String valor;

    public Nota() {}

    public Nota(String valor) {
        this.valor = valor;
    }
    public abstract String toString();

    public abstract boolean esValida();

}
