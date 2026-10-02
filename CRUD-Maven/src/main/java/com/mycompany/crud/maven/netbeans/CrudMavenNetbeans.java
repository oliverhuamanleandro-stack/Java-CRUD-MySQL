package com.crudmysql.crud.maven.netbeans;

import java.util.List;
import java.util.Scanner;

public class CrudMavenNetbeans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioDAO dao = new UsuarioDAO();
        boolean continuar = true;

        try {
            while (continuar) {
                System.out.println("\n=== CRUD Usuarios ===");
                System.out.print("1. Crear usuario\n2. Listar usuarios\n3. Actualizar usuario\n4. Eliminar usuario\n5. Salir\nElige opción: ");
                int opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Correo: ");
                        String correo = sc.nextLine();
                        System.out.print("Edad: ");
                        int edad = sc.nextInt();

                        Usuario u = new Usuario(nombre, correo, edad);
                        dao.create(u);
                        System.out.println("✅ Usuario creado: " + u);
                    }
                    case 2 -> {
                        List<Usuario> usuarios = dao.findAll();
                        if (usuarios.isEmpty()) {
                            System.out.println("⚠️ No hay usuarios registrados.");
                        } else {
                            usuarios.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        System.out.print("ID del usuario a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Usuario u = dao.findById(id);
                        if (u == null) {
                            System.out.println("❌ No existe ese usuario.");
                            break;
                        }
                        System.out.print("Nuevo nombre: ");
                        u.setNombre(sc.nextLine());
                        System.out.print("Nuevo correo: ");
                        u.setCorreo(sc.nextLine());
                        System.out.print("Nueva edad: ");
                        u.setEdad(sc.nextInt());
                        boolean ok = dao.update(u);
                        System.out.println(ok ? "✅ Usuario actualizado." : "❌ Error al actualizar.");
                    }
                    case 4 -> {
                        System.out.print("ID del usuario a eliminar: ");
                        int id = sc.nextInt();
                        boolean ok = dao.delete(id);
                        System.out.println(ok ? "✅ Usuario eliminado." : "❌ No se pudo eliminar.");
                    }
                    case 5 -> {
                        continuar = false;
                        System.out.println("👋 Saliendo del sistema...");
                        continue;
                    }
                    default -> System.out.println("⚠️ Opción no válida");
                }

                if (continuar) {
                    System.out.print("\n¿Deseas realizar otra operación? (s/n): ");
                    String resp = sc.next().trim().toLowerCase();
                    if (!resp.equals("s")) {
                        continuar = false;
                        System.out.println("👋 Saliendo del sistema...");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
