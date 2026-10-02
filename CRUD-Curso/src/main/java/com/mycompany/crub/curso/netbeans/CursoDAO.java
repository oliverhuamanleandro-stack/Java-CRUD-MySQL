package com.mycompany.crud.curso.netbeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void create(Curso curso) {
        String sql = "INSERT INTO cursos (nombre, creditos, docente) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setInt(2, curso.getCreditos());
            stmt.setString(3, curso.getDocente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getString("docente")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public Curso findById(int id) {
        String sql = "SELECT * FROM cursos WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getString("docente")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Curso> findByNombre(String nombre) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos WHERE nombre LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getString("docente")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public boolean update(Curso curso) {
        String sql = "UPDATE cursos SET nombre=?, creditos=?, docente=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setInt(2, curso.getCreditos());
            stmt.setString(3, curso.getDocente());
            stmt.setInt(4, curso.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM cursos WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
