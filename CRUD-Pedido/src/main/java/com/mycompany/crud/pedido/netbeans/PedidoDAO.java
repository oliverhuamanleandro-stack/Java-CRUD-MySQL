package com.mycompany.crud.pedido.netbeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void create(Pedido pedido) {
        String sql = "INSERT INTO pedidos (fecha, cliente_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, pedido.getFecha());
            stmt.setInt(2, pedido.getClienteId());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                pedido.setId(keys.getInt(1));
            }

            DetallePedidoDAO detalleDAO = new DetallePedidoDAO();
            for (DetallePedido d : pedido.getDetalles()) {
                d.setPedidoId(pedido.getId());
                detalleDAO.create(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pedido findById(int id) {
        String sql = "SELECT * FROM pedidos WHERE id=?";
        Pedido pedido = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getInt("cliente_id")
                );
                DetallePedidoDAO detalleDAO = new DetallePedidoDAO();
                pedido.setDetalles(detalleDAO.findByPedidoId(pedido.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getInt("cliente_id")
                );
                DetallePedidoDAO detalleDAO = new DetallePedidoDAO();
                pedido.setDetalles(detalleDAO.findByPedidoId(pedido.getId()));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
}
