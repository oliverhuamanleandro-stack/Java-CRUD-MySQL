package com.crudmysql.crud.maven.netbeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String INSERT_SQL = "INSERT INTO usuarios (nombre, correo, edad) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT id, nombre, correo, edad FROM usuarios WHERE id = ?";
    private static final String SELECT_ALL = "SELECT id, nombre, correo, edad FROM usuarios";
    private static final String UPDATE_SQL = "UPDATE usuarios SET nombre = ?, correo = ?, edad = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM usuarios WHERE id = ?";

    // CREATE
    public void create(Usuario u) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setInt(3, u.getEdad());
            int filas = ps.executeUpdate();
            if (filas == 0) throw new SQLException("Crear usuario falló, no se insertó ninguna fila.");
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) u.setId(rs.getInt(1));
            }
        }
    }

    // READ by id
    public Usuario findById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getInt("edad")
                    );
                } else return null;
            }
        }
    }

    // READ all
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getInt("edad")
                ));
            }
        }
        return lista;
    }

    // UPDATE
    public boolean update(Usuario u) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setInt(3, u.getEdad());
            ps.setInt(4, u.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    // DELETE
    public boolean delete(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
}
