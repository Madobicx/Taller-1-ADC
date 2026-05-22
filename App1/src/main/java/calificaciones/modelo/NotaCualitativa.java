package calificaciones.modelo;

import java.util.ArrayList;
import java.util.List;

public class NotaCualitativa extends Nota {


        public NotaCualitativa(String valor) {
            super(valor);
        }

        @Override
        public String toString() {
            return valor;
        }

        @Override
        public boolean esValida(){
            return valor.equals("APROVADO") || valor.equals("REPROVADO") || valor.equals("PENDIENTE");
        }
}
