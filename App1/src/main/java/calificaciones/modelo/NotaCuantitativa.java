package calificaciones.modelo;

public class NotaCuantitativa extends Nota {
    private double valorNumerico;

    public NotaCuantitativa() {}

    public NotaCuantitativa(String valor){
        super(valor);
        this.valorNumerico = Double.parseDouble(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
    @Override
    public boolean esValida(){
        return (valorNumerico >= 0.0 && valorNumerico <= 5.0);
    }

}
