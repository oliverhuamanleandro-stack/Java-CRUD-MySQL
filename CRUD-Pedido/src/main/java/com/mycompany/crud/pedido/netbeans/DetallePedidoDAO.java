package com.mycompany.crud.pedido.netbeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {

    public void create(DetallePedido detalle) {
        String sql = "INSERT INTO detalle_pedidos (pedido_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalle.getPedidoId());
            stmt.setInt(2, detalle.getProductoId());
            stmt.setInt(3, detalle.getCantidad());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DetallePedido> findByPedidoId(int pedidoId) {
        List<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedidos WHERE pedido_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                detalles.add(new DetallePedido(
                        rs.getInt("id"),
                        rs.getInt("pedido_id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }
}

