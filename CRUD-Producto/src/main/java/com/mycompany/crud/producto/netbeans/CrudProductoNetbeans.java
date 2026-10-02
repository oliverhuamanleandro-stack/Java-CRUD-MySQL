package com.mycompany.crud.producto.netbeans;

import java.util.List;
import java.util.Scanner;

public class CrudProductoNetbeans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductoDAO dao = new ProductoDAO();
        boolean continuar = true;

        try {
            while (continuar) {
                System.out.println("\n=== CRUD Productos ===");
                System.out.print("""
                    1. Crear producto
                    2. Listar productos
                    3. Actualizar producto
                    4. Eliminar producto
                    5. Salir
                    Elige opción: """);

                int opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();
                        System.out.print("Stock: ");
                        int stock = sc.nextInt();
                        sc.nextLine(); // limpiar buffer

                        Producto p = new Producto(nombre, precio, stock);
                        dao.create(p);
                        System.out.println("✅ Producto creado: " + p);
                    }
                    case 2 -> {
                        List<Producto> productos = dao.findAll();
                        if (productos.isEmpty()) {
                            System.out.println("⚠️ No hay productos registrados.");
                        } else {
                            productos.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        System.out.print("ID del producto a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Producto p = dao.findById(id);
                        if (p == null) {
                            System.out.println("❌ No existe ese producto.");
                            break;
                        }
                        System.out.print("Nuevo nombre: ");
                        p.setNombre(sc.nextLine());
                        System.out.print("Nuevo precio: ");
                        p.setPrecio(sc.nextDouble());
                        System.out.print("Nuevo stock: ");
                        p.setStock(sc.nextInt());
                        sc.nextLine();
                        boolean ok = dao.update(p);
                        System.out.println(ok ? "✅ Producto actualizado." : "❌ Error al actualizar.");
                    }
                    case 4 -> {
                        System.out.print("ID del producto a eliminar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        boolean ok = dao.delete(id);
                        System.out.println(ok ? "✅ Producto eliminado." : "❌ No se pudo eliminar.");
                    }
                    case 5 -> {
                        continuar = false;
                        System.out.println("👋 Saliendo del sistema...");
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
