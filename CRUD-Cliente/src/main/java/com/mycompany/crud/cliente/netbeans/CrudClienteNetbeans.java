package com.mycompany.crud.cliente.netbeans;

import java.util.List;
import java.util.Scanner;

public class CrudClienteNetbeans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteDAO dao = new ClienteDAO();
        boolean continuar = true;

        try {
            while (continuar) {
                System.out.println("\n=== CRUD Cliente ===");
                System.out.print("1. Crear cliente\n2. Listar clientes\n3. Actualizar cliente\n4. Eliminar cliente\n5. Salir\nElige opción: ");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Correo: ");
                        String correo = sc.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = sc.nextLine();

                        Cliente c = new Cliente(nombre, correo, telefono);
                        boolean ok = dao.create(c);
                        if (ok) System.out.println("✅ Cliente creado.");
                    }
                    case 2 -> {
                        List<Cliente> clientes = dao.findAll();
                        if (clientes.isEmpty()) {
                            System.out.println("⚠️ No hay clientes registrados.");
                        } else {
                            clientes.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        System.out.print("ID del cliente a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Cliente c = dao.findById(id);
                        if (c == null) {
                            System.out.println("❌ No existe ese cliente.");
                            break;
                        }
                        System.out.print("Nuevo nombre: ");
                        c.setNombre(sc.nextLine());
                        System.out.print("Nuevo correo: ");
                        c.setCorreo(sc.nextLine());
                        System.out.print("Nuevo teléfono: ");
                        c.setTelefono(sc.nextLine());
                        boolean ok = dao.update(c);
                        System.out.println(ok ? "✅ Cliente actualizado." : "❌ Error al actualizar.");
                    }
                    case 4 -> {
                        System.out.print("ID del cliente a eliminar: ");
                        int id = sc.nextInt();
                        boolean ok = dao.delete(id);
                        System.out.println(ok ? "✅ Cliente eliminado." : "❌ No se pudo eliminar.");
                    }
                    case 5 -> {
                        continuar = false;
                        System.out.println("👋 Saliendo del sistema...");
                    }
                    default -> System.out.println("⚠️ Opción no válida");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
