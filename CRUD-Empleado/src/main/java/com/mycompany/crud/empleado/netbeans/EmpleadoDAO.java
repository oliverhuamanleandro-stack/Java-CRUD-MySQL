package com.mycompany.crud.empleado.netbeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private static final String INSERT_SQL =
            "INSERT INTO empleados (nombre, cargo, salarioBase, horasExtra) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID =
            "SELECT * FROM empleados WHERE id = ?";
    private static final String SELECT_ALL =
            "SELECT * FROM empleados";
    private static final String UPDATE_SQL =
            "UPDATE empleados SET nombre = ?, cargo = ?, salarioBase = ?, horasExtra = ? WHERE id = ?";
    private static final String DELETE_SQL =
            "DELETE FROM empleados WHERE id = ?";

    // CREATE
    public void create(Empleado e) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            // Debug: ver a qué base se conectó
            System.out.println("🔗 Conectado a la BD: " + conn.getCatalog());

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCargo());
            ps.setDouble(3, e.getSalarioBase());
            ps.setInt(4, e.getHorasExtra());

            int filas = ps.executeUpdate();
            System.out.println("👉 Filas insertadas: " + filas);

            if (filas == 0) {
                throw new SQLException("⚠️ No se insertó el empleado.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setId(rs.getInt(1));
                }
            }

            System.out.println("✅ Insert realizado correctamente en la tabla empleados.");

        } catch (SQLException ex) {
            System.err.println("❌ Error al insertar empleado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // READ by id
    public Empleado findById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("cargo"),
                            rs.getDouble("salarioBase"),
                            rs.getInt("horasExtra")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // READ all
    public List<Empleado> findAll() {
        List<Empleado> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cargo"),
                        rs.getDouble("salarioBase"),
                        rs.getInt("horasExtra")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // UPDATE
    public boolean update(Empleado e) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCargo());
            ps.setDouble(3, e.getSalarioBase());
            ps.setInt(4, e.getHorasExtra());
            ps.setInt(5, e.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean delete(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
