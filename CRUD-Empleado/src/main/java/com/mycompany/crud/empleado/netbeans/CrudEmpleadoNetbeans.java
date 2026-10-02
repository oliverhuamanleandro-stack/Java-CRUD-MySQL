package com.mycompany.crud.empleado.netbeans;

import java.util.List;
import java.util.Scanner;

public class CrudEmpleadoNetbeans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpleadoDAO dao = new EmpleadoDAO();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== CRUD Empleado ===");
            System.out.print("1. Crear empleado\n2. Listar empleados\n3. Actualizar empleado\n4. Eliminar empleado\n5. Salir\nElige opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();
                    System.out.print("Salario base: ");
                    double salarioBase = sc.nextDouble();
                    System.out.print("Horas extra: ");
                    int horasExtra = sc.nextInt();
                    sc.nextLine();

                    Empleado e = new Empleado(nombre, cargo, salarioBase, horasExtra);
                    dao.create(e);

                    System.out.println("✅ Empleado creado: " + e);

                    // Mostrar todos los empleados después de insertar
                    System.out.println("\n📋 Empleados en la base de datos:");
                    List<Empleado> empleados = dao.findAll();
                    empleados.forEach(System.out::println);
                }
                case 2 -> {
                    List<Empleado> empleados = dao.findAll();
                    if (empleados.isEmpty()) {
                        System.out.println("⚠️ No hay empleados registrados.");
                    } else {
                        empleados.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID del empleado a actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Empleado e = dao.findById(id);
                    if (e == null) {
                        System.out.println("❌ No existe ese empleado.");
                        break;
                    }
                    System.out.print("Nuevo nombre: ");
                    e.setNombre(sc.nextLine());
                    System.out.print("Nuevo cargo: ");
                    e.setCargo(sc.nextLine());
                    System.out.print("Nuevo salario base: ");
                    e.setSalarioBase(sc.nextDouble());
                    System.out.print("Nuevas horas extra: ");
                    e.setHorasExtra(sc.nextInt());
                    sc.nextLine();
                    boolean ok = dao.update(e);
                    System.out.println(ok ? "✅ Empleado actualizado." : "❌ Error al actualizar.");
                }
                case 4 -> {
                    System.out.print("ID del empleado a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    boolean ok = dao.delete(id);
                    System.out.println(ok ? "✅ Empleado eliminado." : "❌ No se pudo eliminar.");
                }
                case 5 -> {
                    continuar = false;
                    System.out.println("👋 Saliendo del sistema...");
                }
                default -> System.out.println("⚠️ Opción no válida");
            }
        }

        sc.close();
    }
}
