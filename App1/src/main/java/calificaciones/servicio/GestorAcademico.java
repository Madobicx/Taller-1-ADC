package calificaciones.servicio;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import calificaciones.modelo.*;
import java.util.Scanner;

public class GestorAcademico {

    private List<Curso> cursos;
    private List<Estudiante> estudiantes;
    private Class<?> clase;
    private Scanner sc = new Scanner(System.in);
    private Nota nota;

    public GestorAcademico() {
        this.cursos = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }
    public void ejecutarMenu() throws Exception {
        int opcion;
        do {
            System.out.println("\n=== Sistema de calificaciones ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar curso");
//            System.out.println("3. Asignar nota a estudiante en curso");
            System.out.println("3. Listar estudiantes");
            System.out.println("4. Listar cursos");
            System.out.println("5. Asignar nota a Estudiante");
            System.out.println("6. Listar notas por curso");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> registratEstudiante();
                case 2 -> registrarCursos();
                case 3 -> System.out.println("Estudiantes: " + listarEstudiantes());
                case 4 -> System.out.println("Cursos: " + listarCursos());
                case 5 -> asignarNota();
                case 6 -> listadoNotasCurso();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
    public void registratEstudiante() throws Exception {
        Estudiante estudiante = new Estudiante();
        this.clase = Estudiante.class;
        Field[] atributos = clase.getDeclaredFields();
        for (Field atributo : atributos) {
            if (!atributo.getName().equals("status")) {
                System.out.println("Digitar " + atributo.getName() + ": ");
                String value = sc.nextLine();
                if(atributo.getName().equals("id")) {
                    if(!(buscarEstudiante(value) == null)) {
                        System.out.println("Estudiante ya registrado con este id.");
                        return;
                    }
                }
                String metodoSet =
                        "set" +
                                atributo.getName().substring(0, 1).toUpperCase() +
                                atributo.getName().substring(1);
                Method metodo;
                if (atributo.getType() == int.class) {
                    metodo = Estudiante.class.getMethod(metodoSet, int.class);
                    metodo.invoke(estudiante, Integer.parseInt(value));
                } else {
                    metodo = Estudiante.class.getMethod(metodoSet, String.class);
                    metodo.invoke(estudiante, value);
                }
            }
        }
        this.estudiantes.add(estudiante);
    }
    public void registrarCursos() throws Exception {
        Curso curso = new Curso();
        this.clase = Curso.class;
        Field[] atributos = clase.getDeclaredFields();
        for (Field atributo : atributos) {
            if (!atributo.getName().equals("registros")) {
                System.out.println("Digitar " + atributo.getName() + ": ");
                String value = sc.nextLine();
                if(atributo.getName().equals("id")) {
                    if(!(buscarCurso(value) == null)) {
                        System.out.println("Curso ya registrado con este id.");
                        return;
                    }
                }
                String metodoSet =
                        "set" +
                                atributo.getName().substring(0, 1).toUpperCase() +
                                atributo.getName().substring(1);
                Method metodo;

                if (atributo.getType() == int.class) {
                    metodo = Curso.class.getMethod(metodoSet, int.class);
                    metodo.invoke(curso, Integer.parseInt(value));
                } else {
                    metodo = Curso.class.getMethod(metodoSet, String.class);
                    metodo.invoke(curso, value);
                }
            }
        }
        this.cursos.add(curso);
    }
    public void asignarNota() {
        if (cursos.isEmpty() || estudiantes.isEmpty()) {
            System.out.println("Se necesita tener cursos y estudiantes registrados.");
            return;
        }
        System.out.println("Cursos: " + listarCursos());
        System.out.println("Id de curso: ");
        String codigo = sc.nextLine();
        Curso curso = buscarCurso(codigo);
        if (curso == null) {
            System.out.println("Curso no encontrado");
        return;
        }
        System.out.println("Estudiantes: " + listarEstudiantes());
        System.out.println("Id de estudiante: ");
        String id = sc.nextLine();
        Estudiante estudiante = buscarEstudiante(id);
        if(estudiante == null){
            System.out.println("Estudiante no encontrado");
            return;
        }
        int tipo;
            boolean estado = true;
            do{
            System.out.println("Tipo de nota: ");
            System.out.println("1. Cuantitativa.");
            System.out.println("2. Cualitativa.");
            tipo = Integer.parseInt(sc.nextLine());
            switch (tipo) {
                case 1 -> {
                    curso.agregarRegistro(new RegistroNota(registroNotaCuantitativa(nota), estudiante));
                    estado = false;
                }
                case 2 -> {
                    curso.agregarRegistro(new RegistroNota(registroNotaCualitativa(nota), estudiante));
                    estado = false;
                }
                default -> System.out.println("Opción no valida");
            }
            }while (estado);
        System.out.println("Nota registrada. ");
    }
    public Nota registroNotaCuantitativa(Nota nota){
        System.out.println("Valor de nota (0.0 - 5.0): ");
        do{
            String value = sc.nextLine();
            nota = new NotaCuantitativa(value);
            if(!nota.esValida()){
                System.out.println("Nota no valida, ingrese otro valor");
            }
        }while(!nota.esValida());
        return nota;
    }
    public Nota registroNotaCualitativa(Nota nota){
        EstadoCualitativo[] estado = EstadoCualitativo.values();
        do {
        System.out.println("Valor de nota: ");
        for(int i = 0; i < estado.length; i++){
            System.out.println(i + ". " + estado[i]);
        }
        System.out.println("Opción: ");
            int value = Integer.parseInt(sc.nextLine());
            nota = new NotaCualitativa(String.valueOf(estado[value]));
            if(!nota.esValida()){
                System.out.println("Nota no valida, ingrese una opcion correcta.");
            }
        }while(!nota.esValida());
        return nota;
    }
    public void listadoNotasCurso(){
        if(cursos.isEmpty()){
            System.out.println("No existen cursos registrados.");
            return;
        }
        System.out.println("Cursos: " + listarCursos());
        System.out.println("Id de curso: ");
        String id = sc.nextLine();
        Curso c = buscarCurso(id);
        if(c == null){
            System.out.println("Curso no encontrado");
            return;
        }
        System.out.println("Notas de " + c.getNombre() + ": ");
        if(c.listarRegistros().isEmpty()){
            System.out.println("No existen registros.");
            return;
        }
        for(RegistroNota r : c.listarRegistros()){
            System.out.println("Estudiante: " + r.getEstudiante().getNombre() + " Nota: " + r.getNota().toString());
        }
    }
    public Estudiante buscarEstudiante(String c){
        int cod = Integer.parseInt(c);
        for (Estudiante id : estudiantes) {
            if(id.getId() == cod){
                return id;
            }
        }
        return null;
    }
    public Curso buscarCurso(String c){
        int cod = Integer.parseInt(c);
        for (Curso curso : cursos) {
            if(curso.getId() == cod){
                return curso;
            }
        }
        return null;
    }
    public List<Estudiante> listarEstudiantes(){
        if(estudiantes.isEmpty()){
            System.out.println("No existen estudiantes registrados.");
            return estudiantes;
        }else {
            return estudiantes;
        }
    }
    public List<Curso> listarCursos(){
        if(cursos.isEmpty()){
            System.out.println("No existen cursos registrados.");
            return cursos;
        }else {
            return cursos;
        }
    }
}
