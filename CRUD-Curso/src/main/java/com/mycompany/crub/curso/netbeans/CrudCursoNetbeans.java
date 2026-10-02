package com.mycompany.crud.curso.netbeans;

import java.util.List;
import java.util.Scanner;

public class CrudCursoNetbeans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CursoDAO dao = new CursoDAO();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== CRUD Cursos ===");
            System.out.println("1. Crear curso");
            System.out.println("2. Listar cursos");
            System.out.println("3. Buscar curso por nombre");
            System.out.println("4. Actualizar curso");
            System.out.println("5. Eliminar curso");
            System.out.println("6. Salir");
            System.out.print("Elige opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Créditos: ");
                    int creditos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Docente: ");
                    String docente = sc.nextLine();

                    dao.create(new Curso(nombre, creditos, docente));
                    System.out.println("✅ Curso creado.");
                }
                case 2 -> {
                    List<Curso> cursos = dao.findAll();
                    cursos.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Nombre a buscar: ");
                    String nombre = sc.nextLine();
                    List<Curso> cursos = dao.findByNombre(nombre);
                    cursos.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("ID del curso a actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Curso curso = dao.findById(id);
                    if (curso == null) {
                        System.out.println("❌ No existe ese curso.");
                        break;
                    }
                    System.out.print("Nuevo nombre: ");
                    curso.setNombre(sc.nextLine());
                    System.out.print("Nuevos créditos: ");
                    curso.setCreditos(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Nuevo docente: ");
                    curso.setDocente(sc.nextLine());

                    boolean ok = dao.update(curso);
                    System.out.println(ok ? "✅ Curso actualizado." : "❌ Error al actualizar.");
                }
                case 5 -> {
                    System.out.print("ID del curso a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    boolean ok = dao.delete(id);
                    System.out.println(ok ? "✅ Curso eliminado." : "❌ No se pudo eliminar.");
                }
                case 6 -> {
                    continuar = false;
                    System.out.println("👋 Saliendo...");
                }
                default -> System.out.println("⚠️ Opción no válida");
            }
        }

        sc.close();
    }
}

