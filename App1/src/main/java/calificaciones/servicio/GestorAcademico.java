package calificaciones.servicio;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import calificaciones.modelo.*;
import java.util.Scanner;
import java.io.*;

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
            System.out.println("3. Listar estudiantes");
            System.out.println("4. Listar cursos");
            System.out.println("5. Asignar nota a Estudiante");
            System.out.println("6. Listar notas por curso");
            System.out.println("7. Exportar reporte completo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> registratEstudiante();
                case 2 -> registrarCursos();
                case 3 -> listarEstudiantes();
                case 4 -> listarCursos();
                case 5 -> asignarNota();
                case 6 -> listadoNotasCurso();
                case 7 -> exportarReporteTxt();
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
                if (atributo.getName().equals("id")) {
                    if (!(buscarEstudiante(value) == null)) {
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
                if (atributo.getName().equals("id")) {
                    if (!(buscarCurso(value) == null)) {
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
        listarCursos();
        System.out.println("Id de curso: ");
        String codigo = sc.nextLine();
        Curso curso = buscarCurso(codigo);
        if (curso == null) {
            System.out.println("Curso no encontrado");
            return;
        }
        listarEstudiantes();
        System.out.println("Id de estudiante: ");
        String id = sc.nextLine();
        Estudiante estudiante = buscarEstudiante(id);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado");
            return;
        }
        int tipo;
        boolean estado = true;
        do {
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
        } while (estado);
        System.out.println("Nota registrada. ");
    }

    public Nota registroNotaCuantitativa(Nota nota) {
        System.out.println("Valor de nota (0.0 - 5.0): ");
        do {
            String value = sc.nextLine();
            nota = new NotaCuantitativa(value);
            if (!nota.esValida()) {
                System.out.println("Nota no valida, ingrese otro valor");
            }
        } while (!nota.esValida());
        return nota;
    }

    public Nota registroNotaCualitativa(Nota nota) {
        EstadoCualitativo[] estado = EstadoCualitativo.values();
        do {
            System.out.println("Valor de nota: ");
            for (int i = 0; i < estado.length; i++) {
                System.out.println(i + ". " + estado[i]);
            }
            System.out.println("Opción: ");
            int value = Integer.parseInt(sc.nextLine());
            nota = new NotaCualitativa(String.valueOf(estado[value]));
            if (!nota.esValida()) {
                System.out.println("Nota no valida, ingrese una opcion correcta.");
            }
        } while (!nota.esValida());
        return nota;
    }

    public Estudiante buscarEstudiante(String c) {
        int cod = Integer.parseInt(c);
        for (Estudiante id : estudiantes) {
            if (id.getId() == cod) {
                return id;
            }
        }
        return null;
    }

    public Curso buscarCurso(String c) {
        int cod = Integer.parseInt(c);
        for (Curso curso : cursos) {
            if (curso.getId() == cod) {
                return curso;
            }
        }
        return null;
    }

    public void listarEstudiantes() {
        listarEstudiantes(System.out);
    }

    public void listarEstudiantes(PrintStream out) {
        if (estudiantes.isEmpty()) {
            out.println("No existen estudiantes registrados.");
            return;
        }
        out.printf("| %-5s | %-15s | %-15s |%n", "ID", "NOMBRE", "APELLIDO");
        for (Estudiante e : estudiantes) {
            out.println(e.toString());
        }
    }

    public void listarCursos() {
        listarCursos(System.out);
    }

    public void listarCursos(PrintStream out) {
        if (cursos.isEmpty()) {
            out.println("No existen cursos registrados.");
            return;
        }
        out.printf("| %-5s | %-20s |%n", "ID", "NOMBRE");
        for (Curso c : cursos) {
            out.println(c.toString());
        }
    }

    public void listadoNotasCurso() {
        if (cursos.isEmpty()) {
            System.out.println("No existen cursos registrados.");
            return;
        }
        listarCursos();
        System.out.println("Id de curso: ");
        String id = sc.nextLine();
        Curso c = buscarCurso(id);
        if (c == null) {
            System.out.println("Curso no encontrado");
            return;
        }
        listadoNotasCurso(c, System.out);
    }

    public void listadoNotasCurso(Curso c, PrintStream out) {
        out.println("Notas de " + c.getNombre() + ": ");
        if (c.listarRegistros().isEmpty()) {
            out.println("No existen registros.");
            return;
        }
        for (RegistroNota r : c.listarRegistros()) {
            out.println("Estudiante: " + r.getEstudiante().getNombre() + " | Nota: " + r.getNota().toString());
        }
    }

    public void exportarEstudiantesTxt() throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("estudiantes.txt"))) {
            listarEstudiantes(ps);
        }
        System.out.println("Archivo generado: estudiantes.txt");
    }

    public void exportarCursosTxt() throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("cursos.txt"))) {
            listarCursos(ps);
        }
        System.out.println("Archivo generado: cursos.txt");
    }

    public void exportarNotasCursoTxt() throws IOException {
        if (cursos.isEmpty()) {
            System.out.println("No existen cursos registrados.");
            return;
        }
        listarCursos();
        System.out.println("Id de curso: ");
        String id = sc.nextLine();
        Curso c = buscarCurso(id);
        if (c == null) {
            System.out.println("Curso no encontrado");
            return;
        }
        String nombreArchivo = "notas_" + c.getNombre().replace(" ", "_") + ".txt";
        try (PrintStream ps = new PrintStream(new FileOutputStream(nombreArchivo))) {
            listadoNotasCurso(c, ps);
        }
        System.out.println("Archivo generado: " + nombreArchivo);
    }

    public void exportarReporteTxt() throws IOException {
        try (PrintStream ps = new PrintStream(new FileOutputStream("reporte_academico.txt"))) {
            ps.println("========== REPORTE ACADÉMICO ==========");
            ps.println();
            ps.println("--- ESTUDIANTES ---");
            listarEstudiantes(ps);
            ps.println();
            ps.println("--- CURSOS ---");
            listarCursos(ps);
            ps.println();
            ps.println("--- NOTAS POR CURSO ---");
            for (Curso c : cursos) {
                listadoNotasCurso(c, ps);
                ps.println();
            }
        }
        System.out.println("Archivo generado: reporte_academico.txt");
    }
}