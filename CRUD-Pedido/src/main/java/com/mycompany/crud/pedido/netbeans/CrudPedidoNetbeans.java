package com.mycompany.crud.pedido.netbeans;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class CrudPedidoNetbeans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoDAO pedidoDAO = new PedidoDAO();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== CRUD Pedidos ===");
            System.out.println("1. Crear pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("3. Buscar pedido por ID");
            System.out.println("4. Salir");
            System.out.print("Elige opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String fechaStr = sc.nextLine();
                    Date fecha = Date.valueOf(fechaStr);
                    System.out.print("Cliente ID: ");
                    int clienteId = sc.nextInt();
                    sc.nextLine();

                    Pedido pedido = new Pedido(fecha, clienteId);

                    System.out.print("¿Cuántos detalles quieres agregar? ");
                    int n = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < n; i++) {
                        System.out.println("Detalle " + (i + 1));
                        System.out.print("Producto ID: ");
                        int productoId = sc.nextInt();
                        System.out.print("Cantidad: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();
                        pedido.getDetalles().add(new DetallePedido(pedido.getId(), productoId, cantidad));
                    }

                    pedidoDAO.create(pedido);
                    System.out.println("✅ Pedido creado con ID: " + pedido.getId());
                }
                case 2 -> {
                    List<Pedido> pedidos = pedidoDAO.findAll();
                    pedidos.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID del pedido: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Pedido pedido = pedidoDAO.findById(id);
                    if (pedido != null) {
                        System.out.println(pedido);
                    } else {
                        System.out.println("❌ Pedido no encontrado.");
                    }
                }
                case 4 -> continuar = false;
                default -> System.out.println("⚠️ Opción no válida");
            }
        }

        sc.close();
    }
}
